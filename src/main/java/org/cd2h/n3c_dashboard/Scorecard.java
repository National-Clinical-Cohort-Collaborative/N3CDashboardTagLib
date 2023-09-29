package org.cd2h.n3c_dashboard;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.cd2h.N3CDashboardTagLib.util.LocalProperties;
import org.cd2h.N3CDashboardTagLib.util.PropertyLoader;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfDocumentInfo;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfLinkAnnotation;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.ColumnDocumentRenderer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.ParagraphRenderer;

//
// Page 1
//

// select cdm_name,cdm_version,run_date,contribution_date,payload_count_estimate from manifest_payload_timestamp where institutionid ='https://ror.org/036jqmy94';

// user/project counts (from our end)

// select first_name,last_name,project_title,data_security_tier,ispublic from projects_users where institutionid='https://ror.org/036jqmy94';
// flag for this one needs to be resolved

//
// Page 2
//

// select category,concept,sitepercentn,pcornetmeanpct,pcornetmedianiqr,allsitesmedianiqr,rankwithinallsites from pi_summary_table  where institutionid ='https://ror.org/036jqmy94' order by 1,2;

//
// Page 3
//

// select los,visit_count from ip_los   where institutionid ='https://ror.org/036jqmy94' order by 1;

//
// Page 4
//

// top block
// select percent_null,percent_majority_unharmonized,percent_invalid,percent_inferred,percent_harmonized from site_specific_unit_harmonization   where institutionid ='https://ror.org/036jqmy94';

// bottom block ?

// Integrated Health Care System - N3C Collaboration and Data Profile

public class Scorecard {
	static Logger logger = Logger.getLogger(Scorecard.class);
	static String pathPrefix = null;
	static String logoPath = null;
	static int logoHeight = 0;
	static String publicationMapPath = null;
	static LocalProperties prop_file = null;
	static Connection conn = null;

	static Document document = null;
	static FontProgram fontProgram = null;
	static PdfFont font = null;
	
	static Color headerColor = null; //new DeviceRgb(255, 255, 255);
	static Color headerBackground = null; //new DeviceRgb(64,90,140);
	static float headerBackgroundAlpha = 1.0f;

	static DecimalFormat formatter = new DecimalFormat("###,###,###"); 
	static DecimalFormat pctFormatter = new DecimalFormat("##0.00");
	
	static boolean isDataContributor = false;
	static boolean hasN3CGrantMention = false;
	static boolean hasSubcontracts = false;
	static boolean hasCollaboratingSites = false;
	static boolean hasProjects = false;
	static boolean hasPublishingSites = false;

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
		PropertyConfigurator.configure(args[0]);
		initialize();

		logger.debug(PdfFontFactory.getRegisteredFonts());

		processSites();
	}
	
	// 00thqtb16
	// 036jqmy94
	// 03wmf1y16
	static void processSites() throws SQLException, IOException {
		PreparedStatement stmt = conn.prepareStatement("select institutionid,institutionname from n3c_admin.dua_master where institutionname !~'[/#]' order by 2");
//		PreparedStatement stmt = conn.prepareStatement("select institutionid,institutionname from n3c_admin.dua_master where institutionid = 'https://ror.org/03wmf1y16' order by 2");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String ror = rs.getString(1);
			String name = rs.getString(2);
			logger.info("site: " + ror + "\t:\t" + name);
			try {
				// first set the conditional rendering flags
				isDataContributor = isDataContributor(ror);
				hasN3CGrantMention = hasN3CGrantMention(ror);
				hasSubcontracts = hasSubcontracts(ror);
				hasCollaboratingSites = hasCollaboratingSites(ror);
				hasProjects = hasProjects(ror);
				hasPublishingSites = hasPublishingSites(ror);
				
				generateSite(ror, name);
			} catch (Exception e) {
				logger.error("\t*** exception raised");
				e.printStackTrace();
			}
		}
		stmt.close();
	}
	
	static void initialize() throws ClassNotFoundException, SQLException {
		prop_file = PropertyLoader.loadProperties("scorecard");
		conn = getConnection();

		pathPrefix = prop_file.getProperty("scorecard_path");
		logoPath = prop_file.getProperty("logo_path");
		logoHeight = prop_file.getIntProperty("logo_height");
		
		headerColor = hex2Rgb(prop_file.getProperty("header_color", "#aaaaaa"));
		headerBackground = hex2Rgb(prop_file.getProperty("header_background", "#000000"));

		publicationMapPath = prop_file.getProperty("publication_map_path");
	}
	
	//
	// set page background color: https://kb.itextpdf.com/home/it7kb/faq/how-to-change-the-color-of-pages
	//

	static PageSize getPageSize() {
		return PageSize.LETTER.rotate();
	}
	
	static void generateSite(String ror, String name) throws IOException, SQLException {
		PdfWriter writer = new PdfWriter(pathPrefix + name.replaceAll(" ", "_") + ".pdf");
		PdfDocument pdfDoc = new PdfDocument(writer);
//        pdfDoc.addEventHandler(PdfDocumentEvent.START_PAGE, new PageBackgroundsEventHandler());

        font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);

		PdfDocumentInfo info = pdfDoc.getDocumentInfo();
		info.setAuthor("National COVID-19 Cohort Collaborative");
		info.setTitle("N3C Data Scorecard: " + name);
		info.setCreator("N3C Labs");
		info.setSubject("data scorecard");

		// Creating a Document
		document = new Document(pdfDoc, getPageSize());

		pdfDoc.addNewPage();
		generateCollaborationScorecard(ror, name, document);
		
//		if (isDataContributor) {
//			document.add(new AreaBreak(AreaBreakType.NEXT_AREA));
//			generateDataScorecard(ror, name, document);			
//			document.add(new AreaBreak(AreaBreakType.NEXT_AREA));
//			generateQualityScorecard(ror, name, document);			
//		}

		if (hasN3CGrantMention || hasSubcontracts) {
			document.add(new AreaBreak(AreaBreakType.NEXT_AREA));
		}
		if (hasN3CGrantMention) {
			addHeader("NIH Grant Awards to " + name + " with Mentions of N3C");
			generateGrantTable(ror, name, document);
		}
		if (hasSubcontracts) {
			addHeader("N3C-related Subcontract Awards to " + name);
			generateSubcontractTable(ror, name, document);
		}
		if (hasProjects) {
			document.add(new AreaBreak(AreaBreakType.NEXT_AREA));
			addHeader("Enclave Projects with Members from " + name);
			generateProjectTable(ror, name, document);
		}
		if (hasPublishingSites) {
			document.add(new AreaBreak(AreaBreakType.NEXT_AREA));
			addHeader("Publications with Authors from " + name);
			generatePubTable(ror, name, document);
		}
		document.close();
	}
	
	static boolean isDataContributor(String ror) throws SQLException {
		int count = 0;
		PreparedStatement stmt = conn.prepareStatement("select count(*) from n3c_maps.site_mapping where ror = ?");
		stmt.setString(1, ror);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			count = rs.getInt(1);
		}
		stmt.close();
		return count > 0;
	}
	
	static boolean hasN3CGrantMention(String ror) throws SQLException {
		int count = 0;
		PreparedStatement stmt = conn.prepareStatement("select count(*)"
													+ " from nih_exporter_current.ror_binding natural join nih_exporter_current.n3c"
													+ " where ror_id = ?");
		stmt.setString(1, ror);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			count = rs.getInt(1);
		}
		stmt.close();
		return count > 0;
	}
	
	static boolean hasCollaboratingSites(String ror) throws SQLException {
		int count = 0;
		PreparedStatement stmt = conn.prepareStatement("select count(*)"
														+ " from n3c_collaboration.organization_edge as foo1,n3c_collaboration.organization_edge as foo2"
														+ " where foo1.project_uid=foo2.project_uid and foo1.ror_id != foo2.ror_id\n"
														+ " and foo1.ror_id = ?");
		stmt.setString(1, ror);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			count = rs.getInt(1);
		}
		stmt.close();
		return count > 0;
	}
	
	static boolean hasProjects(String ror) throws SQLException {
		return getSiteCount("select project_count from n3c_collaboration.organization_organization where ror_id = ?", ror) > 0;
	}
	
	static boolean hasPublishingSites(String ror) throws SQLException {
		int count = 0;
		PreparedStatement stmt = conn.prepareStatement("select count(*)"
													+ " from n3c_collaboration.publication_organization"
													+ " where ror_id = ?");
		stmt.setString(1, ror);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			count = rs.getInt(1);
		}
		stmt.close();
		return count > 0;
	}
	
	static boolean hasSubcontracts(String ror) throws SQLException {
		int count = 0;
		PreparedStatement stmt = conn.prepareStatement("select count(*)"
													+ " from n3c_collaboration.subcontracts"
													+ " where subaward_ror = ?");
		stmt.setString(1, ror);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			count = rs.getInt(1);
		}
		stmt.close();
		return count > 0;
	}
	
	static void generateCollaborationScorecard(String ror, String name, Document document) throws MalformedURLException, SQLException {
		Table table = new Table(5).useAllAvailableWidth();
		
		// Row 1 - logo and institution info

		Cell logoCell = new Cell().setBorder(Border.NO_BORDER);
		Image image = new Image(ImageDataFactory.create(logoPath));
		image.setHeight(logoHeight);
//		image.setAutoScale(true);
		logoCell.add(image).setWidth(20);
		table.addCell(logoCell);
		
		Cell headerCell = new Cell(1,4).setBorder(Border.NO_BORDER);
		Text header = new Text("N3C Collaboration Profile: " + name)
				.setFontSize(18)
				.setFont(font)
				.setBold()
				.setFontColor(headerColor);
		Paragraph head = new Paragraph()
					.setBackgroundColor(headerBackground, headerBackgroundAlpha)
					.setFirstLineIndent(5)
					.add(header)
					.setMarginBottom(3);
		headerCell.add(head);
		table.addCell(headerCell);
		
		document.add(table);
		table = new Table(3).useAllAvailableWidth();
		
		// Row 2 - Metrics

		Cell metric1 = getTileCell(1, "Users", name, ror);
		metric1.setWidth(30);
		table.addCell(metric1);
		
		Cell collab2 = getTileCell(1, "Collaborating Institutions", name, ror);
		collab2.setWidth(40);
		table.addCell(collab2);

		Cell metric3 = getTileCell(1, "Grants mentioning N3C", name, ror);
		metric3.setWidth(30);
		table.addCell(metric3);

		document.add(table);
		table = new Table(2).useAllAvailableWidth();
		
		// Row 3 - Collaborations

		Cell collab3 = getTileCell(1,"Sites collaborating with ", name, ror);
		table.addCell(collab3);

		Cell collab1 = getTileCell(1, "Sites publishing with ", name, ror);
		table.addCell(collab1);
		
		// Adding Table to document
		document.add(table);
	}
	
	static void generateDataScorecard(String ror, String name, Document document) throws MalformedURLException, SQLException {
		Table table = new Table(5).useAllAvailableWidth();
		
		// Row 1 - logo and institution info

		Cell logoCell = new Cell().setBorder(Border.NO_BORDER);
		Image image = new Image(ImageDataFactory.create(logoPath));
		image.setHeight(logoHeight);
//		image.setAutoScale(true);
		logoCell.add(image).setWidth(20);
		table.addCell(logoCell);
		
		Cell headerCell = new Cell(1,4).setBorder(Border.NO_BORDER);
		Text header = new Text("N3C Data Profile: " + name)
				.setFontSize(18)
				.setFont(font)
				.setBold()
				.setFontColor(headerColor);
		Paragraph head = new Paragraph()
					.setBackgroundColor(headerBackground, headerBackgroundAlpha)
					.setFirstLineIndent(5)
					.add(header)
					.setMarginBottom(3);
		headerCell.add(head);
		table.addCell(headerCell);
		
		document.add(table);
		table = new Table(5).useAllAvailableWidth();
		
		// Row 2 - Metrics

		Cell metric1 = getTileCell(1, "Clinical Data Model", name, ror);
		metric1.setWidth(20);
		table.addCell(metric1);
		
		Cell metric2 = getTileCell(1, "Latest Submission", name, ror);
		metric2.setWidth(20);
		table.addCell(metric2);
		
		Cell metric3 = getTileCell(1, "# Submissions", name, ror);
		metric3.setWidth(20);
		table.addCell(metric3);
		
		Cell metric4 = getTileCell(1, "Total Patients", name, ror);
		metric4.setWidth(20);
		table.addCell(metric4);
		
		Cell metric5 = getTileCell(1, "Total Visits", name, ror);
		metric5.setWidth(20);
		table.addCell(metric5);
		
		document.add(table);
		table = new Table(4).useAllAvailableWidth();
		
		// Row 3 - Quality

		Cell quality1 = getTileCell(1, "Percent Null Units",name,  ror);
		quality1.setWidth(20);
		table.addCell(quality1);
		
		Cell quality2 = getTileCell(1, "Percent Invalid Units", name, ror);
		quality2.setWidth(20);
		table.addCell(quality2);
		
		Cell quality3 = getTileCell(1, "Percent Inferred Units", name, ror);
		quality3.setWidth(20);
		table.addCell(quality3);
		
		Cell quality4 = getTileCell(1, "Percent Harmonized Units", name, ror);
		quality4.setWidth(20);
		table.addCell(quality4);
		
		// Adding Table to document
		document.add(table);
	}
	
	static void generateQualityScorecard(String ror, String name, Document document) throws MalformedURLException, SQLException {
		addHeader("Executive Summary");
		Paragraph exec = new Paragraph();
		Text execblock = new Text(getTextBlock("executive summary"))
						.setFontSize(9);
		exec.add(execblock);
		document.add(exec);
		generateExecSummaryTable(ror, name, document);
	}
	
	static Color getColor(String type) {
		switch (type) {
		case "COM":
			return hex2Rgb("#ffa600");
		case "CTR":
			return hex2Rgb("#AD1181");
		case "CTSA":
			return hex2Rgb("#8406D1");
		case "GOV":
			return hex2Rgb("#09405A");
		case "N3C":
			return hex2Rgb("#007bff");
		case "REGIONAL":
			return hex2Rgb("#a6a6a6");
		case "UNAFFILIATED":
			return hex2Rgb("#ff7155");
		default:
			return ColorConstants.BLACK;
		}
	}
	
	static Cell getTileCell(int columns, String header, String name, String ror) throws SQLException, MalformedURLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Cell cell = new Cell(1,columns)
						.setBorder(Border.NO_BORDER)
						.setTextAlignment(TextAlignment.CENTER)
//						.setBackgroundColor(ColorConstants.WHITE)
						;
		switch (header) {
		case "Users":
			addCellHeader(cell, header);
			int userCount = getSiteCount("select count(*) from n3c_admin.gsuite_view where ror_id = ? and enclave='TRUE'", ror);
			Paragraph userPara = new Paragraph(userCount + " Enclave Users");
			cell.add(userPara);

			int otherCount = getSiteCount("select count(*) from n3c_admin.gsuite_view where ror_id = ? and enclave='FALSE'", ror);
			Paragraph otherPara = new Paragraph(otherCount + " Addt'l N3C Members");
			cell.add(otherPara);
			break;
		case "Enclave Projects":
			addCellHeader(cell, header);
			break;
		case "Collaborating Institutions":
			addCellHeader(cell, header);
			Table table2 = new Table(2).useAllAvailableWidth();
			Cell cell1 = new Cell(1,columns)
					.setBorder(Border.NO_BORDER)
					.setTextAlignment(TextAlignment.CENTER)
					.setWidth(5)
					;
			Cell cell2 = new Cell(1,columns)
					.setBorder(Border.NO_BORDER)
					.setTextAlignment(TextAlignment.CENTER)
					.setWidth(95)
					;
			stmt = conn.prepareStatement("select bar.org_type,count(*)"
										+ " from n3c_collaboration.organization_organization as foo, n3c_collaboration.organization_edge as foo1,"
										+ "      n3c_collaboration.organization_organization as bar, n3c_collaboration.organization_edge as bar1"
										+ " where foo.ror_id=foo1.ror_id and foo1.project_uid=bar1.project_uid"
										+ " and bar.ror_id=bar1.ror_id and foo.ror_id!=bar.ror_id"
										+ " and foo.ror_id = ?"
										+ " group by 1 order by 2 desc,1");
			stmt.setString(1, ror);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Paragraph collaboratorPara = new Paragraph(rs.getInt(2) + " " + rs.getString(1));
				collaboratorPara.setFontColor(getColor(rs.getString(1)));
				cell1.add(collaboratorPara);
			}
			stmt.close();
			Paragraph collbars = new Paragraph();
			
			if (hasCollaboratingSites) {
				Image theColls = new Image(ImageDataFactory.create(getCollaboratorImagePath(ror)));
				theColls.setAutoScale(true);
				collbars.add(theColls);
			} else {
				Text notice = new Text("None")
//						.setFontSize(18)
//						.setFont(font)
						.setItalic();
				collbars.add(notice);
			}
			cell2.add(collbars);
			table2.addCell(cell1);
			table2.addCell(cell2);
			cell.add(table2);
			break;
		case "Grants mentioning N3C":
			addCellHeader(cell, header);
			if (hasN3CGrantMention) {
				stmt = conn.prepareStatement("select count(*),sum(award_amount)"
						+ " from nih_exporter_current.ror_binding natural join nih_exporter_current.n3c"
						+ " where ror_id = ?");
				stmt.setString(1, ror);
				rs = stmt.executeQuery();
				while (rs.next()) {
					Paragraph awardPara = new Paragraph(rs.getInt(1) + " awards");
					cell.add(awardPara);
					Paragraph costPara = new Paragraph("$" + formatter.format(rs.getInt(2)) + " total amount");
					cell.add(costPara);
				}
				stmt.close();
			} else {
				Paragraph notice = new Paragraph("None")
//						.setFontSize(18)
//						.setFont(font)
						.setItalic();
				cell.add(notice);
			}
			break;
		case "Clinical Data Model":
			addCellHeader(cell, header);
			stmt = conn.prepareStatement("select cdm_name,cdm_version from n3c_scorecard.manifest_payload_timestamp where institutionid = ?");
			stmt.setString(1, ror);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Paragraph cdmPara = new Paragraph(rs.getString(1) + " " + rs.getString(2));
				cell.add(cdmPara);
			}
			stmt.close();
			break;
		case "Latest Submission":
			addCellHeader(cell, header);
			stmt = conn.prepareStatement("select coalesce(run_date,'None') as run_date from n3c_scorecard.manifest_payload_timestamp where institutionid = ?");
			stmt.setString(1, ror);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Paragraph cdmPara = new Paragraph(rs.getString(1));
				cell.add(cdmPara);
			}
			stmt.close();
			break;
		case "# Submissions":
			addCellHeader(cell, header);
			stmt = conn.prepareStatement("select payload_count_estimate from n3c_scorecard.manifest_payload_timestamp where institutionid = ?");
			stmt.setString(1, ror);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Paragraph cdmPara = new Paragraph(formatter.format(rs.getInt(1)));
				cell.add(cdmPara);
			}
			stmt.close();
			break;
		case "Total Patients":
			addCellHeader(cell, header);
			stmt = conn.prepareStatement("select sitepercentn from n3c_scorecard.pi_summary_table where category='Total' and concept='Persons' and institutionid = ?");
			stmt.setString(1, ror);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Paragraph cdmPara = new Paragraph(formatter.format(rs.getInt(1)));
				cell.add(cdmPara);
			}
			stmt.close();
			break;
		case "Total Visits":
			addCellHeader(cell, header);
			stmt = conn.prepareStatement("select sitepercentn from n3c_scorecard.pi_summary_table where category='Total' and concept='Visits' and institutionid = ?");
			stmt.setString(1, ror);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Paragraph cdmPara = new Paragraph(formatter.format(rs.getInt(1)));
				cell.add(cdmPara);
			}
			stmt.close();
			break;
		case "Percent Null Units":
			addCellHeader(cell, header);
			stmt = conn.prepareStatement("select percent_null from n3c_scorecard.site_specific_unit_harmonization where institutionid = ?");
			stmt.setString(1, ror);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Paragraph cdmPara = new Paragraph(pctFormatter.format(rs.getDouble(1)) + "%");
				cell.add(cdmPara);
			}
			stmt.close();
			break;
		case "Percent Invalid Units":
			addCellHeader(cell, header);
			stmt = conn.prepareStatement("select percent_invalid from n3c_scorecard.site_specific_unit_harmonization where institutionid = ?");
			stmt.setString(1, ror);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Paragraph cdmPara = new Paragraph(pctFormatter.format(rs.getDouble(1)) + "%");
				cell.add(cdmPara);
			}
			stmt.close();
			break;
		case "Percent Inferred Units":
			addCellHeader(cell, header);
			stmt = conn.prepareStatement("select percent_inferred from n3c_scorecard.site_specific_unit_harmonization where institutionid = ?");
			stmt.setString(1, ror);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Paragraph cdmPara = new Paragraph(pctFormatter.format(rs.getDouble(1)) + "%");
				cell.add(cdmPara);
			}
			stmt.close();
			break;
		case "Percent Harmonized Units":
			addCellHeader(cell, header);
			stmt = conn.prepareStatement("select percent_harmonized from n3c_scorecard.site_specific_unit_harmonization where institutionid = ?");
			stmt.setString(1, ror);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Paragraph cdmPara = new Paragraph(pctFormatter.format(rs.getDouble(1)) + "%");
				cell.add(cdmPara);
			}
			stmt.close();
			break;
		case "Publications":
			addCellHeader(cell, header);
			if (hasPublishingSites) {
			} else {
				Paragraph notice = new Paragraph("None")
//						.setFontSize(18)
//						.setFont(font)
						.setItalic();
				cell.add(notice);
			}
			break;
		case "Sites collaborating with ":
			if (hasCollaboratingSites) {
				addCellHeader(cell, header  + name);
				
				int investigatorCount = getSiteCount("select investigator_count from n3c_collaboration.organization_organization where ror_id = ?", ror);
				Paragraph investigatorPara = new Paragraph(investigatorCount + " Local Investigators, ");
				investigatorPara.setFontSize(10);

				int membershipCount = getSiteCount("select membership_count from n3c_collaboration.organization_organization where ror_id = ?", ror);
				investigatorPara.add(membershipCount + " Project Memberships, ");

				int projectCount = getSiteCount("select project_count from n3c_collaboration.organization_organization where ror_id = ?", ror);
				investigatorPara.add(projectCount + " Enclave Projects");

				cell.add(investigatorPara);
			} else {
				addCellHeader(cell, "N3C Collaboration Map");
				Paragraph notice = new Paragraph("No collaborating sites identified for this site");
				cell.add(notice);
			}
			Paragraph map2 = new Paragraph();
			Image theMap2 = new Image(ImageDataFactory.create(getProjectsImagePath(ror)));
			theMap2.setAutoScale(true);
			map2.add(theMap2);
			map2.add(generateLink("Click here for live version.", "https://n3c.cd2h.org/dashboard/collaboration.jsp"));
			map2.setFontSize(9);
			cell.add(map2);
			break;
		case "Sites publishing with ":
			if (hasPublishingSites) {
				addCellHeader(cell, header  + name);
				stmt = conn.prepareStatement(
						"select count(distinct last_name||' '||first_name) from scholar_profile.authorship_map where ror_id = ?");
				stmt.setString(1, ror);
				rs = stmt.executeQuery();
				Paragraph cdmPara = null;
				while (rs.next()) {
					cdmPara = new Paragraph(formatter.format(rs.getInt(1)) + " Local Authors, ");
				}
				stmt.close();

				stmt = conn.prepareStatement(
						"select count(*) from scholar_profile.citation where id in (select id from scholar_profile.authorship natural join scholar_profile.authorship_map as bar where ror_id = ?)");
				stmt.setString(1, ror);
				rs = stmt.executeQuery();
				while (rs.next()) {
					cdmPara.add(formatter.format(rs.getInt(1)) + " Publications, ");
				}
				stmt.close();

				stmt = conn.prepareStatement("select count(*) from scholar_profile.authorship natural join scholar_profile.authorship_map where ror_id = ?");
				stmt.setString(1, ror);
				rs = stmt.executeQuery();
				while (rs.next()) {
					cdmPara.add(formatter.format(rs.getInt(1)) + " Authorships");
				}
				stmt.close();

				cdmPara.setFontSize(10);
				cell.add(cdmPara);
			} else {
				addCellHeader(cell, "N3C Publication Map");
				Paragraph notice = new Paragraph("No publications identified for this site");
				cell.add(notice);
			}
			Paragraph map1 = new Paragraph();
			Image theMap = new Image(ImageDataFactory.create(getPublicationsImagePath(ror)));
			theMap.setAutoScale(true);
			map1.add(theMap);
			map1.add(generateLink("Click here for live version.", "https://n3c.cd2h.org/dashboard/publications-map.jsp"));
			map1.setFontSize(9);
			cell.add(map1);
			break;
		default:
			break;
		}
		return cell;
	}
	
	static int getSiteCount(String query, String ror) throws SQLException {
		int count = 0;
		
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, ror);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			count = rs.getInt(1);
		}
		stmt.close();
		return count;
	}
	
	static String getProjectsImagePath(String ror) {
		if (!hasCollaboratingSites) {
			return publicationMapPath + "projects.png";
		} else if (ror.startsWith("https:")) {
			return publicationMapPath + "projects_sites/" + ror.substring(ror.lastIndexOf('/') + 1) + ".png";
		} else {
			return publicationMapPath + "projects_sites/" + ror + ".png";
		}
	}

	static String getPublicationsImagePath(String ror) {
		if (!hasPublishingSites) {
			return publicationMapPath + "publications.png";
		} else if (ror.startsWith("https:")) {
			return publicationMapPath + "publications_sites/" + ror.substring(ror.lastIndexOf('/') + 1) + ".png";
		} else {
			return publicationMapPath + "publications_sites/" + ror + ".png";
		}
	}

	static String getCollaboratorImagePath(String ror) {
		if (ror.startsWith("https:")) {
			return publicationMapPath + "collaborator_sites/" + ror.substring(ror.lastIndexOf('/') + 1) + ".png";
		} else {
			return publicationMapPath + "collaborator_sites/" + ror + ".png";
		}
	}

	static void generateProjectTable(String ror, String org, Document document) throws SQLException {
		// Creating a table
		Table table = new Table(5).useAllAvailableWidth();

		// Adding cells to the table
		addHeaderCell(table, "Status");
		addHeaderCell(table, "Title");
		addHeaderCell(table, "Lead Investigator");
		addHeaderCell(table, "Members From This Site");
		addHeaderCell(table, "Collaborating Organizations");
		PreparedStatement inclstmt = conn
				.prepareStatement("select uid,title,lead_investigator,accessing_institution,workspace_status,dur_project_id"
								+ " from n3c_admin.enclave_project"
								+ " where uid in (select project_uid"
												+ " from n3c_admin.enclave_project_members, n3c_admin.enclave_project, palantir.n3c_user"
												+ " where project_uid=uid and enclave_project_members.orcid_id=n3c_user.orcid_id"
												+ " and ror_id = ?)"
								+ " order by 5,2");
		inclstmt.setString(1, ror);
		ResultSet inclrs = inclstmt.executeQuery();
		while (inclrs.next()) {
			String uid = inclrs.getString(1);
			String title = inclrs.getString(2);
			String lead_investigator = inclrs.getString(3);
			String accessing_institution = inclrs.getString(4);
			String status = inclrs.getString(5);
			String dur = inclrs.getString(6);
			addCell(table, status);
			addCell(table, title);

			Cell leadCell = new Cell();
			Paragraph leadInv = new Paragraph(lead_investigator);
			leadInv.setFontSize(9);
			leadCell.add(leadInv);
			Paragraph leadOrg = new Paragraph("(" + accessing_institution + ")");
			leadOrg.setFontSize(7);
			leadCell.add(leadOrg);
			table.addCell(leadCell);
			
			Cell authorCell = new Cell();
			PreparedStatement authStmt = conn.prepareStatement("select last_name,first_name"
															+ " from n3c_admin.enclave_project_members,n3c_admin.enclave_project,palantir.n3c_user"
															+ " where project_uid=uid and enclave_project_members.orcid_id=n3c_user.orcid_id"
															+ " and ror_id = ?"
															+ " and uid = ?"
															+ " order by 1,2");
			authStmt.setString(1, ror);
			authStmt.setString(2, uid);
			ResultSet authrs = authStmt.executeQuery();
			while (authrs.next()) {
				String lastName = authrs.getString(1);
				String firstName = authrs.getString(2);
				Paragraph author = new Paragraph("\u2022\u00a0" + lastName + ",\u00a0" + firstName);
				author.setFontSize(9);
				authorCell.add(author);
			}
			authStmt.close();
			table.addCell(authorCell);

			Cell orgCell = new Cell();
			PreparedStatement orgStmt = conn.prepareStatement("select ror_name,count(*)"
															+ " from n3c_admin.enclave_project_members,n3c_admin.enclave_project,palantir.n3c_user"
															+ " where project_uid=uid and enclave_project_members.orcid_id=n3c_user.orcid_id"
															+ " and ror_id != ?"
															+ " and uid = ?"
															+ " group by 1"
															+ " order by 1");
			orgStmt.setString(1, ror);
			orgStmt.setString(2, uid);
			ResultSet orgrs = orgStmt.executeQuery();
			while (orgrs.next()) {
				String orgName = orgrs.getString(1);
				int orgCount = orgrs.getInt(2);
//				Paragraph collOrg = new Paragraph("\u2022\u00a0" + orgName.replaceAll(" ", "\u00a0") + "\u00a0(" + orgCount + ")");
				Paragraph collOrg = new Paragraph("\u2022\u00a0" + orgName + " (" + orgCount + ")");
				collOrg.setFontSize(9);
				orgCell.add(collOrg);
			}
			orgStmt.close();
			table.addCell(orgCell);
}
		inclstmt.close();

		// Adding Table to document
		document.add(table);

	}
	
	static void generatePubTable(String ror, String org, Document document) throws SQLException {
		// Creating a table
		Table table = new Table(2).useAllAvailableWidth();

		// Adding cells to the table
		addHeaderCell(table, "Title");
		addHeaderCell(table, "Authors");
		PreparedStatement inclstmt = conn
				.prepareStatement("select title,external_url,id from scholar_profile.citation\n"
						+ "where id in (select id from scholar_profile.authorship natural join scholar_profile.authorship_map as bar where ror_id = ?)");
		inclstmt.setString(1, ror);
		ResultSet inclrs = inclstmt.executeQuery();
		while (inclrs.next()) {
			String name = inclrs.getString(1);
			String url = inclrs.getString(2);
			int pubid = inclrs.getInt(3);
			addLinkCell(table, name, url, TextAlignment.LEFT);
			Cell authorCell = new Cell();
			PreparedStatement authStmt = conn.prepareStatement("select\n"
					+ "				last_name, first_name, seqnum\n"
					+ "			from scholar_profile.authorship as foo natural join scholar_profile.authorship_map\n"
					+ "			where foo.id = ? and ror_id = ? order by seqnum");
			authStmt.setInt(1, pubid);
			authStmt.setString(2, ror);
			ResultSet authrs = authStmt.executeQuery();
			while (authrs.next()) {
				String lastName = authrs.getString(1);
				String firstName = authrs.getString(2);
				int rank = authrs.getInt(3);
				Paragraph author = new Paragraph("\u2022\u00a0" + lastName + ",\u00a0" + firstName);
				author.setFontSize(9);
				authorCell.add(author);
			}
			authStmt.close();
			table.addCell(authorCell);
		}
		inclstmt.close();

		// Adding Table to document
		document.add(table);

	}
	
	static void generateExecSummaryTable(String ror, String org, Document document) throws SQLException {
		// Creating a table
		Table table = new Table(7).useAllAvailableWidth();

		// Adding cells to the table
		addHeaderCell(table, "Category");
		addHeaderCell(table, "Concept");
		addHeaderCell(table, "SitePercentN");
		addHeaderCell(table, "PCORNETmeanpct");
		addHeaderCell(table, "PCORNETMedianIQR");
		addHeaderCell(table, "AllSitesMedianIQR");
		addHeaderCell(table, "RankWithinAllSites");
		PreparedStatement inclstmt = conn
				.prepareStatement("select category,concept,sitepercentn,pcornetmeanpct,pcornetmedianiqr,allsitesmedianiqr,rankwithinallsites"
									+ " from n3c_scorecard.pi_summary_table  where institutionid = ? order by 1,2;");
		inclstmt.setString(1, ror);
		ResultSet inclrs = inclstmt.executeQuery();
		while (inclrs.next()) {
			String category = inclrs.getString(1);
			String concept = inclrs.getString(2);
			String sitepercentn = inclrs.getString(3);
			String pcornetmeanpct = inclrs.getString(4);
			String pcornetmedianiqr = inclrs.getString(5);
			String allsitesmedianiqr = inclrs.getString(6);
			String rankwithinallsites = inclrs.getString(7);
			addCell(table, category);
			addCell(table, concept);
			addCell(table, sitepercentn);
			addCell(table, pcornetmeanpct);
			addCell(table, pcornetmedianiqr);
			addCell(table, allsitesmedianiqr);
			addCell(table, rankwithinallsites);
		}
		inclstmt.close();

		// Adding Table to document
		document.add(table);

	}

	static void generateGrantTable(String ror, String org, Document document) throws SQLException {
		// Creating a table
		Table table = new Table(6).useAllAvailableWidth();

		// Adding cells to the table
		addHeaderCell(table, "Project Number");
		addHeaderCell(table, "Title");
		addHeaderCell(table, "IC");
		addHeaderCell(table, "Contact PI");
		addHeaderCell(table, "Fiscal Year");
		addHeaderCell(table, "Award Amount");
		PreparedStatement inclstmt = conn.prepareStatement("select project_num,project_title,ic,contact_pi,fiscal_year,award_amount"
														+ " from nih_exporter_current.ror_binding natural join nih_exporter_current.n3c"
														+ " where ror_id = ?"
														+ " order by contact_pi,fiscal_year");
		inclstmt.setString(1, ror);
		ResultSet inclrs = inclstmt.executeQuery();
		while (inclrs.next()) {
			String number = inclrs.getString(1);
			String title = inclrs.getString(2);
			String ic = inclrs.getString(3);
			String pi = inclrs.getString(4);
			String year = inclrs.getString(5);
			int award = inclrs.getInt(6);
			addCell(table, number);
			addCell(table, title);
			addCell(table, ic);
			addCell(table, pi);
			addCell(table, year);
			addCell(table, "$" + formatter.format(award), TextAlignment.RIGHT);
		}
		inclstmt.close();

		// Adding Table to document
		document.add(table);

	}

	static void generateSubcontractTable(String ror, String org, Document document) throws SQLException {
		// Creating a table
		Table table = new Table(3);

		// Adding cells to the table
		addHeaderCell(table, "Project Number");
		addHeaderCell(table, "Title");
		addHeaderCell(table, "Primary Site");
		PreparedStatement inclstmt = conn.prepareStatement("select project_number,title,primary_site"
														+ " from n3c_collaboration.subcontracts"
														+ " where subaward_ror = ?"
														+ " order by 1,2");
		inclstmt.setString(1, ror);
		ResultSet inclrs = inclstmt.executeQuery();
		while (inclrs.next()) {
			String number = inclrs.getString(1);
			String title = inclrs.getString(2);
			String primary = inclrs.getString(3);
			addCell(table, number);
			addCell(table, title);
			addCell(table, primary);
		}
		inclstmt.close();

		// Adding Table to document
		document.add(table);

	}

	static String getTextBlock(String id) throws SQLException {
		String content = null;
		PreparedStatement stmt = conn.prepareStatement("select caption from n3c_scorecard.text_block where tag = ?");
		stmt.setString(1, id);
		ResultSet rs = stmt.executeQuery();
		rs.next();	
		content = rs.getString(1).replaceAll("</?p>", "");
		stmt.close();
		return content;
	}

	static void generateSmallPubTable(Document document) throws SQLException {
		// Creating a table
		Table table = new Table(2).useAllAvailableWidth();

		// Adding cells to the table
		addHeaderCell(table, "Title");
		addHeaderCell(table, "Authors");
		PreparedStatement inclstmt = conn
				.prepareStatement("select title,external_url,id from scholar_profile.citation\n"
						+ "where id in (select id from scholar_profile.authorship natural join scholar_profile.authorship_map as bar where ror_id = ?)");
		inclstmt.setString(1, "https://ror.org/036jqmy94");
		ResultSet inclrs = inclstmt.executeQuery();
		while (inclrs.next()) {
			String name = inclrs.getString(1);
			String url = inclrs.getString(2);
			int pubid = inclrs.getInt(3);
			addLinkCell(table, name, url, TextAlignment.LEFT);
			addCell(table, pubid + "", TextAlignment.RIGHT);
		}
		inclstmt.close();

		// Adding Table to document
		document.add(table);

	}

	static Link generateLink(String anchor, String URI) {
		Rectangle rect = new Rectangle(0, 0);
		PdfLinkAnnotation annotation = new PdfLinkAnnotation(rect);
		PdfAction action = PdfAction.createURI(URI);
		annotation.setAction(action);
		annotation.setBorderStyle(PdfAnnotation.STYLE_UNDERLINE);
		Link link = new Link(anchor, annotation);
		return link;
	}

	static void addItem(String label, String value) {
		Text header = new Text(label + ": ");
		header.setBold();
		Text body = new Text(value != null ? value : "");
		Paragraph para = new Paragraph();
		para.add(header);
		para.add(body);
		document.add(para);
	}

	static void addHeader(String label) {
		Text header = new Text(label + ": ")
						.setBold()
						.setFontColor(headerColor);
		Paragraph para = new Paragraph()
							.setFirstLineIndent(5)
							.setBackgroundColor(headerBackground, headerBackgroundAlpha);
		para.add(header);
		document.add(para);
	}

	static void addCellHeader(Cell cell, String label) {
		Text header = new Text(label)
						.setBold()
						.setFontColor(headerColor);
		Paragraph para = new Paragraph()
							.setTextAlignment(TextAlignment.CENTER)
							.setMarginTop(20)
							.setBackgroundColor(headerBackground, headerBackgroundAlpha);
		para.add(header);
		cell.add(para);
	}

	static void addHeaderCell(Table table, String content) {
		addHeaderCell(table, content, TextAlignment.LEFT);
	}

	static void addHeaderCell(Table table, String content, TextAlignment alignment) {
		Paragraph paragraph = new Paragraph(content != null ? content : "");
		paragraph.setFontSize(9);
		paragraph.setBold();
		Cell cell = new Cell().add(paragraph);
		cell.setTextAlignment(alignment);
		cell.setBackgroundColor(headerBackground, 0.25f);
		table.addHeaderCell(cell);
	}

	static void addCell(Table table, String content) {
		addCell(table, content, TextAlignment.LEFT);
	}

	static void addCell(Table table, String content, TextAlignment alignment) {
		Paragraph paragraph = new Paragraph(content != null ? content : "");
		paragraph.setFontSize(9);
		Cell cell = new Cell().add(paragraph);
		cell.setTextAlignment(alignment);
		table.addCell(cell);
	}

	static void addLinkCell(Table table, String anchor, String URI, TextAlignment alignment) {
		Rectangle rect = new Rectangle(0, 0);
		PdfLinkAnnotation annotation = new PdfLinkAnnotation(rect);
		PdfAction action = PdfAction.createURI(URI);
		annotation.setAction(action);
		annotation.setBorderStyle(PdfAnnotation.STYLE_UNDERLINE);
		Link link = new Link(anchor, annotation);
		Paragraph paragraph = new Paragraph();
		paragraph.setFontSize(9);
		paragraph.add(link);
		Cell cell = new Cell().add(paragraph);
		cell.setTextAlignment(alignment);
		table.addCell(cell);
	}

	static void addCheckCell(Table table, String content, TextAlignment alignment) {
		Paragraph paragraph = new Paragraph();
		paragraph.setFont(font);
		paragraph.setFontSize(9);
		paragraph.add(content != null ? content : "");
		Cell cell = new Cell().add(paragraph);
		cell.setTextAlignment(alignment);
		table.addCell(cell);
	}

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		Properties props = new Properties();
		props.setProperty("user", prop_file.getProperty("jdbc.user"));
		props.setProperty("password", prop_file.getProperty("jdbc.password"));
		Connection conn = DriverManager.getConnection(prop_file.getProperty("jdbc.url"), props);
		conn.setAutoCommit(false);
		return conn;
	}

	public static Color hex2Rgb(String colorStr) {
	    return new DeviceRgb(
	            Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
	            Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
	            Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
	}
	
	static class HeaderParagraphRenderer extends ParagraphRenderer {
		float y;
		Paragraph modelElement = null;

		public HeaderParagraphRenderer(Paragraph modelElement) {
			super(modelElement);
			this.modelElement = modelElement;
		}
		
		public ParagraphRenderer getNextRenderer() {
			return new HeaderParagraphRenderer(modelElement);
		}

		@Override
		public void drawBorder(DrawContext drawContext) {
			super.drawBorder(drawContext);
			y = getOccupiedAreaBBox().getBottom();
		}

		public float getY() {
			return y;
		}
	}

	static class MyColumnDocumentRenderer extends ColumnDocumentRenderer {

		Rectangle[] columns2;

		public MyColumnDocumentRenderer(Document document, Rectangle[] columns1, Rectangle[] columns2) {
			super(document, columns1);
			this.columns2 = columns2;
		}

		@Override
		protected PageSize addNewPage(PageSize customPageSize) {
			PageSize size = super.addNewPage(customPageSize);
			columns = columns2;
			return size;
		}
	}

	static class PageBackgroundsEventHandler implements IEventHandler {
        @Override
        public void handleEvent(Event currentEvent) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) currentEvent;
            PdfPage page = docEvent.getPage();

            int pageNumber = docEvent.getDocument().getNumberOfPages();

            // Background color will be applied to the first page and all even pages
            if (pageNumber > 1) {
                return;
            }

            PdfCanvas canvas = new PdfCanvas(page);
            Rectangle rect = page.getPageSize();
            canvas
                    .saveState()
                    .setFillColor(ColorConstants.LIGHT_GRAY)
                    .rectangle(rect)
                    .fillStroke()
                    .restoreState();
        }
    }
}

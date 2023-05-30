package org.cd2h.n3c_dashboard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.cd2h.N3CDashboardTagLib.util.LocalProperties;
import org.cd2h.N3CDashboardTagLib.util.PropertyLoader;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfDocumentInfo;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfLinkAnnotation;
import com.itextpdf.layout.ColumnDocumentRenderer;
import com.itextpdf.layout.Document;
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

	public static void main(String[] args)
			throws IOException, ClassNotFoundException, SQLException, InterruptedException {
		PropertyConfigurator.configure(args[0]);
		initialize();

		logger.debug(PdfFontFactory.getRegisteredFonts());
		generateSample("Iowa");
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
		return PageSize.LETTER;
	}
	
	static void generateSample(String id) throws IOException, SQLException {

		PdfWriter writer = new PdfWriter(pathPrefix + id + ".pdf");
		PdfDocument pdfDoc = new PdfDocument(writer);
		font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);

		PdfDocumentInfo info = pdfDoc.getDocumentInfo();
		info.setAuthor("National COVID-19 Cohort Collaborative");
		info.setTitle("N3C Data Scorecard: " + id);
		info.setCreator("N3C Labs");
		info.setSubject("data scorecard");

		// Creating a Document
		document = new Document(pdfDoc, getPageSize());
		
		logger.debug("document: " + document.getPageEffectiveArea(getPageSize()) + " top: " + document.getTopMargin() + " bottom: " + document.getBottomMargin() + " left: " + document.getBottomMargin() + " right: " + document.getBottomMargin());

		pdfDoc.addNewPage();

		Paragraph lead = new Paragraph();
		Image image = new Image(ImageDataFactory.create(logoPath));
		image.setHeight(logoHeight);
		lead.add(image);
		document.add(lead);

		Text header = new Text("N3C Data Scorecard: " + id)
						.setFontSize(18)
						.setFont(font)
						.setBold()
						.setFontColor(headerColor);
		Paragraph head = new Paragraph()
							.setBackgroundColor(headerBackground, headerBackgroundAlpha)
							.setFirstLineIndent(5)
							.add(header)
							.setMarginBottom(5);
		HeaderParagraphRenderer renderer = new HeaderParagraphRenderer(head);
		head.setNextRenderer(renderer);
		document.add(head);

		// Set column parameters
		float offSet = 36;
		float gutter = 23;
		float columnWidth = (getPageSize().getWidth() - offSet * 2) / 2 - gutter;
		float columnHeight1 = renderer.getY() - offSet * 2;
		Rectangle[] columns1 = {
				new Rectangle(offSet, offSet, columnWidth, columnHeight1),
				new Rectangle(offSet + columnWidth + gutter, offSet, columnWidth, columnHeight1)
				};
//		float columnHeight2 = getPageSize()().getHeight() - offSet * 2;
		Rectangle[] columns2 = {
				new Rectangle(offSet, offSet, getPageSize().getWidth() - offSet * 2, getPageSize().getHeight() - offSet * 2)
//	             new Rectangle(offSet, offSet, columnWidth, columnHeight2),
//	             new Rectangle(offSet + columnWidth + gutter, offSet, columnWidth, columnHeight2)
		};
		document.setRenderer(new MyColumnDocumentRenderer(document, columns1, columns2));

		addHeader("Sites publishing with " + id);
		Paragraph map = new Paragraph();
		Image theMap = new Image(ImageDataFactory.create(publicationMapPath + "publications_sites/The_University_of_Iowa.png"));
		theMap.setAutoScale(true);
		map.add(theMap);
		map.add(generateLink("Click here for live version.", "https://n3c.cd2h.org/dashboard/publications-map.jsp"));
		map.setFontSize(9);
		document.add(map);

		addHeader("Sites currently collaborating with " + id);
		map = new Paragraph();
		theMap = new Image(ImageDataFactory.create(publicationMapPath + "projects_sites/University_of_Iowa.png"));
		theMap.setAutoScale(true);
		map.add(theMap);
		map.add(generateLink("Click here for live version.", "https://n3c.cd2h.org/dashboard/publications-map.jsp"));
		map.setFontSize(9);
		document.add(map);

		document.add(new AreaBreak(AreaBreakType.NEXT_AREA));

		addHeader("All N3C Publication Collaborations");
		map = new Paragraph();
		theMap = new Image(ImageDataFactory.create(publicationMapPath + "publications.png"));
		theMap.setAutoScale(true);
		map.add(theMap);
		map.add(generateLink("Click here for live version.", "https://n3c.cd2h.org/dashboard/publications-map.jsp"));
		map.setFontSize(9);
		document.add(map);

		addHeader(id + " Publications");
		generateSmallPubTable(document);

		document.add(new AreaBreak(AreaBreakType.NEXT_AREA));

		addHeader("Sites publishing with " + id);
		map = new Paragraph();
		theMap = new Image(ImageDataFactory.create(publicationMapPath + "sites/The_University_of_Iowa.png"));
		theMap.setAutoScale(true);
		map.add(theMap);
		map.add(generateLink("Click here for live version.", "https://n3c.cd2h.org/dashboard/publications-map.jsp"));
		map.setFontSize(9);
		document.add(map);
		generatePubTable(document);

		document.close();
	}

	static void generatePubTable(Document document) throws SQLException {
		// Creating a table
		Table table = new Table(2).useAllAvailableWidth();

		// Adding cells to the table
		addHeaderCell(table, "Title");
		addHeaderCell(table, "Authors (rank)");
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
			Cell authorCell = new Cell();
			PreparedStatement authStmt = conn.prepareStatement("select\n"
					+ "				last_name, first_name, seqnum\n"
					+ "			from scholar_profile.authorship as foo natural join scholar_profile.authorship_map\n"
					+ "			where foo.id = ? and ror_id = ? order by seqnum");
			authStmt.setInt(1, pubid);
			authStmt.setString(2, "https://ror.org/036jqmy94");
			ResultSet authrs = authStmt.executeQuery();
			while (authrs.next()) {
				String lastName = authrs.getString(1);
				String firstName = authrs.getString(2);
				int rank = authrs.getInt(3);
				Paragraph author = new Paragraph("\u2022\u00a0" + lastName + ",\u00a0" + firstName + "\u00a0(" + rank +")");
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
}

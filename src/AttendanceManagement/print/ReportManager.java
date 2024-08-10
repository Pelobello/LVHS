
package AttendanceManagement.print;

import AttendanceManagement.ModelRecords.ParamenterAttendance;
import AttendanceManagement.ModelRecords.ParameterEmployee;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.System.Logger.Level;

import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.log4j.Logger;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;



public class ReportManager {
     private static final Logger logger = Logger.getLogger(ReportManager.class);
private static ReportManager instance;
private JasperReport jReport;
private JasperReport teachersReport;
public static ReportManager getInstance() throws IOException{
    if (instance == null) {
        instance = new ReportManager();
        
    }
        return instance;
    
}

  static {
        Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);
    }
    private ReportManager() throws IOException {
         try {
            compileReport();
        } catch (JRException e) {
            e.printStackTrace();
            // Handle exception as needed
        }
        
    }
    public void compileReport()throws JRException,IOException{
       
        jReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/AttendanceManagement/print/AttendanceReportV2.jrxml"));
        teachersReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/AttendanceManagement/print/TeachersReport.jrxml"));
    }
    public void printTeachersReport(ParameterEmployee data)throws JRException{
        Map<String,Object> para = new HashMap<>();
        para.put("school", data.getSchool());
        para.put("schoolImage", data.getSchoolImage());
       
         JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getField());
         JasperPrint print = JasperFillManager.fillReport(teachersReport, para,dataSource);
         viewReport(print);
    }
    public void printAttendanceReport(ParamenterAttendance data)throws JRException, IOException{
          Map<String, Object> para = new HashMap<>();

        para.put("Name", data.getName());
        para.put("Month", data.getMonth());
        para.put("Year", data.getYear());
        para.put("Principal", data.getPrincipal());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getFields());
        JasperPrint print = JasperFillManager.fillReport(jReport, para, dataSource);
        

        viewReport(print);
      
     
    }
    private void viewReport(JasperPrint print)throws JRException {
        JasperViewer.viewReport(print,false);
        
    }
    public void exportReportTeachertoExcel(ParameterEmployee data,String outputPath) throws JRException{
         Map<String,Object> para = new HashMap<>();
        para.put("school", data.getSchool());
        para.put("schoolImage", data.getSchoolImage());
           
         JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getField());
         JasperPrint print = JasperFillManager.fillReport(teachersReport, para,dataSource);
          JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputPath));
        SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
        configuration.setOnePagePerSheet(false);
        configuration.setRemoveEmptySpaceBetweenRows(true);
        exporter.setConfiguration(configuration);
        
        exporter.exportReport();
    }
     public void exportReportTeachertoPdf(ParameterEmployee data,String outputPath) throws JRException{
          Map<String,Object> para = new HashMap<>();
        para.put("school", data.getSchool());
        para.put("schoolImage", data.getSchoolImage());
           
         JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getField());
         JasperPrint print = JasperFillManager.fillReport(teachersReport, para,dataSource);
         JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputPath));
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);

        exporter.exportReport();
     }
     public void exportDtrReportToPdf(ParamenterAttendance data, String outputPath) throws JRException, FileNotFoundException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Name", data.getName());
        parameters.put("Month", data.getMonth());
        parameters.put("Year", data.getYear());
        parameters.put("Principal", data.getPrincipal());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getFields());
        JasperPrint print = JasperFillManager.fillReport(jReport, parameters, dataSource);

        // Export to PDF
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputPath));
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);

        exporter.exportReport();
    }

    public void exportReportToExcel(ParamenterAttendance data, String outputPath) throws JRException {
        Map<String, Object> para = new HashMap<>();
        para.put("Name", data.getName());
        para.put("Month", data.getMonth());
        para.put("Year", data.getYear());
         para.put("Principal", data.getPrincipal());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getFields());
        JasperPrint print = JasperFillManager.fillReport(jReport, para, dataSource);
        
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputPath));
        SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
        configuration.setOnePagePerSheet(true);
        configuration.setRemoveEmptySpaceBetweenRows(true);
        exporter.setConfiguration(configuration);
        
        exporter.exportReport();
    }
   
}

package edu.usd.csc.pdb;

import edu.usd.csc.pdb.db.Database;
import edu.usd.csc.pdb.db.PatientResult;
import edu.usd.csc.pdb.db.ProdResult;
import edu.usd.csc.pdb.db.ScriptResult;
import edu.usd.csc.pdb.records.PatientRecord;
import edu.usd.csc.pdb.records.ScriptRecord;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @author Menno.VanDiermen, Kurtis Van Gent, Krishna Pareek
 */
public class FXMLDocumentController implements Initializable {
    
    Database db = new Database();
    
    
    @FXML private MenuItem menuPha, menuDoc, menuInv;
    @FXML private TextField CIDfield, LNfield, PIDfield, PRODfield;
    @FXML private TextField addFN, addLN, addMonth, addDay, addYear, addAddr, addZip,
                            pID, pFN, pLN, pDOB, pZIP, pAddr, pPid, pProd, pDose;
    @FXML private TableView<PatientResult> results;
    @FXML private TableView<ScriptResult> recent;
    @FXML private TableView<ScriptResult> scripts;    
    @FXML private TableColumn<PatientResult, Integer> zip;
    @FXML private TableColumn<PatientResult, String> cid, rcid, fn, ln, dob, addr;
    @FXML private TableColumn<ProdResult, String> pid, prod, dose;
    @FXML private TableColumn<ProdResult, Integer> stock;
    @FXML private TableColumn<ScriptResult, String> scid, spid, med, sdose, date;
    @FXML private TableColumn<ScriptResult, Boolean> filled;
    @FXML private TextArea dialogTA;
    @FXML private Label idText;
    @FXML private Button yes, no, addPatient, addConfirm, addCancel, idClose, prescribe, confirmPrescribe;
    
    private static PatientResult selectedPatient;
    private static ScriptResult selectedScript;
    private static int newID;
        
    @FXML
    private void handleSwitchButtonAction(ActionEvent event) throws IOException {
        Stage stage; Parent root;
        
        if(event.getSource().equals(menuPha)) {
            stage = (Stage) menuPha.getParentPopup().getOwnerWindow();
            root = FXMLLoader.load(getClass().getResource("PharmaView.fxml"));
        }
        else if(event.getSource().equals(menuDoc)) {
            stage = (Stage) menuDoc.getParentPopup().getOwnerWindow();
            root = FXMLLoader.load(getClass().getResource("DocView.fxml"));
        }
        else {
            stage = (Stage) menuInv.getParentPopup().getOwnerWindow();
            root = FXMLLoader.load(getClass().getResource("StockView.fxml"));
        }
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    @FXML
    private void handleTextFieldAction(ActionEvent event) throws Exception {
        ObservableList result; long t = System.currentTimeMillis();
        if(event.getSource().equals(CIDfield)) {
            String query = CIDfield.textProperty().get();
            result = db.searchCID(Integer.parseInt(query));
            
        } 
        else if(event.getSource().equals(LNfield)) {
            String query = LNfield.textProperty().get();
            result = db.searchLN(query);
        }
        else if(event.getSource().equals(PIDfield)) {
            String query = PIDfield.textProperty().get();
            result = db.searchPID(Integer.parseInt(query));
        } 
        else {
            String query = PRODfield.textProperty().get();
            result = db.searchPROD(query);
        }
        results.setItems(result);
        System.out.println("Results retrieved in: " + (System.currentTimeMillis() - t) + "milliseconds.");
    }
    
    @FXML
    private void handlePatientSelect(MouseEvent event) throws IOException {
        Object obj = ((Node) event.getSource());
        selectedPatient = ((PatientResult) ((TableView) event.getSource()).getSelectionModel().getSelectedItem());
        if(selectedPatient != null) {
            showPatientDialog();
        }
    }
    
    @FXML
    private void handleDocPatientSelect(MouseEvent event) {
        Object obj = ((Node) event.getSource());
        selectedPatient = ((PatientResult) ((TableView) event.getSource()).getSelectionModel().getSelectedItem());
        pID.setText(selectedPatient.getCID()); pFN.setText(selectedPatient.getFN()); pLN.setText(selectedPatient.getLN());
        pDOB.setText(selectedPatient.getDOB()); pZIP.setText("" + selectedPatient.getZIP()); pAddr.setText(selectedPatient.getADDR());
    }
    
    @FXML
    private void handleScriptSelect(MouseEvent event) throws IOException {
        Object obj = ((Node) event.getSource());
        selectedScript = ((ScriptResult) ((TableView) event.getSource()).getSelectionModel().getSelectedItem());
        if(selectedScript != null) {
            showFillDialog();
        }
    }
    
    @FXML
    private void handleAddButton(ActionEvent event) throws Exception {
        if(event.getSource() == addConfirm) {
            String fn = addFN.getText(); String ln = addLN.getText();
            String dob = addMonth.getText() + "/" + addDay.getText() + "/" + addYear.getText();
            String addr = addAddr.getText(); String zip = addZip.getText();
            newID = Database.nextClientID();
            db.insertPatientRecord(new PatientRecord(fn, ln, dob, addr, Integer.parseInt(zip)));
            showIDDialog();
            addConfirm.getScene().getWindow().hide();
        } else {
            addCancel.getScene().getWindow().hide();
        }
    }
    
    @FXML
    private void handleOpenNewPatient(ActionEvent event) throws IOException {
        if(event.getSource() == addPatient) showAddPatientDialog();
    }
    
    @FXML
    private void handleFillButton(ActionEvent event) {
        if(event.getSource().equals(yes)) {
            selectedScript.setFILLED(true);
            
            ScriptRecord sr = db.getScript(selectedScript.getSID());
            sr.setFilled(true);
            db.updateScriptRecord(sr);
            
            db.subtractFill(sr.getPid());
            
            yes.getScene().getWindow().hide();
        } else {
            no.getScene().getWindow().hide();
        }
    }
    
    @FXML
    private void handleOpenPrescribe(ActionEvent event) throws IOException {
        if (event.getSource() == prescribe) {
            showPrescribeDialog();
        }
    }
    
    @FXML
    private void handleConfirmPrescribe(ActionEvent event) {
            if(event.getSource() == confirmPrescribe) {
                ScriptRecord sr = new ScriptRecord(
                        Integer.parseInt(selectedPatient.getCID()), 
                        Integer.parseInt(pPid.getText()),
                        Database.getDate());
                db.insertScriptRecord(sr);
                confirmPrescribe.getScene().getWindow().hide();
            }
    }
    
    @FXML
    private void handlePrescribePid(ActionEvent event) {
        if(event.getSource() == pPid) {
            ProdResult sr = new ProdResult(db.getProduct(newID));
            pProd.setText(sr.getPROD());
            pDose.setText(sr.getDOSE());
        }
    }
    
    @FXML
    private void handleSelectMed(MouseEvent event) {
        Object obj = ((Node) event.getSource());
        ProdResult prod = ((ProdResult) ((TableView) event.getSource()).getSelectionModel().getSelectedItem());
        pPid.setText(prod.getPID());
        pProd.setText(prod.getPROD());
        pDose.setText(prod.getDOSE());
    }
    
    @FXML
    private void handleIDClose(ActionEvent event) {
        if(event.getSource() == idClose) {
            idClose.getScene().getWindow().hide();
        }
    }
    
    @FXML
    private void showPrescribeDialog() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("PrescribeDialog.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    @FXML
    private void showIDDialog() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("IDDialog.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    @FXML
    private void showAddPatientDialog() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddPatientDialog.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    @FXML 
    private void showPatientDialog() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("PatientDialog.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    @FXML
    private void showFillDialog() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FillDialog.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // INITIALIZE COLUMNS IF THEY EXIST
        if(cid != null) {
            cid.setCellValueFactory(new PropertyValueFactory<PatientResult, String>("CID"));
        }
        if(rcid != null) {
            rcid.setCellValueFactory(new PropertyValueFactory<PatientResult, String>("CID"));
        }
        if(fn != null) {
            fn.setCellValueFactory(new PropertyValueFactory<PatientResult, String>("FN"));
        }
        if(ln != null) {
            ln.setCellValueFactory(new PropertyValueFactory<PatientResult, String>("LN"));            
        }
        if(addr != null) {
            addr.setCellValueFactory(new PropertyValueFactory<PatientResult, String>("ADDR"));
        }
        if(dob != null) {
            dob.setCellValueFactory(new PropertyValueFactory<PatientResult, String>("DOB"));
        }
        if(zip != null) {
            zip.setCellValueFactory(new PropertyValueFactory<PatientResult, Integer>("ZIP"));
        }
        if(pid != null) {
            pid.setCellValueFactory(new PropertyValueFactory<ProdResult, String>("PID"));
        }
        if(prod != null) {
            prod.setCellValueFactory(new PropertyValueFactory<ProdResult, String>("PROD"));
        }
        if(dose != null) {
            dose.setCellValueFactory(new PropertyValueFactory<ProdResult, String>("DOSE"));
        }
        if(stock != null) {
            stock.setCellValueFactory(new PropertyValueFactory<ProdResult, Integer>("STOCK"));
        }
        if(scid != null) {
            scid.setCellValueFactory(new PropertyValueFactory<ScriptResult, String>("CID"));
        }
        if(spid != null) {
            spid.setCellValueFactory(new PropertyValueFactory<ScriptResult, String>("PID"));
        }
        if(med != null) {
            med.setCellValueFactory(new PropertyValueFactory<ScriptResult, String>("PROD"));
        }
        if(sdose != null) {
            sdose.setCellValueFactory(new PropertyValueFactory<ScriptResult, String>("DOSE"));
        }
        if(date != null) {
            date.setCellValueFactory(new PropertyValueFactory<ScriptResult, String>("DATE"));
        }
        if(filled != null) {
            filled.setCellValueFactory(new PropertyValueFactory<ScriptResult, Boolean>("FILLED"));
            filled.setCellFactory(CheckBoxTableCell.forTableColumn(filled));
        }
        if(dialogTA != null) {
            StringBuilder sb = new StringBuilder();
            sb
                    .append("CID: ")
                    .append(selectedPatient.getCID())
                    .append(System.lineSeparator())
                    .append("Name: ")
                    .append(selectedPatient.getFN())
                    .append(" ")
                    .append(selectedPatient.getLN())
                    .append(System.lineSeparator())
                    .append("DOB: ")
                    .append(selectedPatient.getDOB())
                    .append(System.lineSeparator())
                    .append("Address: ")
                    .append(selectedPatient.getADDR())
                    .append(System.lineSeparator())
                    .append("ZIP: ")
                    .append(selectedPatient.getZIP());
            dialogTA.setText(sb.toString());
        }
        if(scripts != null) {
            scripts.setItems(db.scriptsForCid(Integer.parseInt(selectedPatient.getCID())));
        }
        if(recent != null) {
            recent.setItems(db.getRecent());
        }
        if(idText != null) {
            idText.setText(String.format("%06d", newID));
        }
    }
}
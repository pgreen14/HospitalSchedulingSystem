import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.stream.XMLStreamException;


public class PatientInfoFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static String INPUT_FILE = "/Users/perigreen/HospitalSchedulingSystem/BTE324Final/resources/schedulerDataIn.xml";
	
	private JPanel contentPane;
	private JTextField txtfirstName;
	private JTextField txtlastName;
	private JTextField txtEmail;
	private JTextField txtssn;
	private JTextField txtdob;
	//private String SCHEDULER_FILE = "resources//schedulerDataIn";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientInfoFrame frame = new PatientInfoFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public PatientInfoFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPatients = new JLabel("Patients");
		lblPatients.setHorizontalAlignment(SwingConstants.CENTER);
		lblPatients.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPatients.setBounds(100, 14, 414, 14);
		contentPane.add(lblPatients);
		
		
		JButton btnNewButton = new JButton("Add a New Patient");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddNewPatientFrame newFrame = new AddNewPatientFrame();
				newFrame.setVisible(true);					
			}
		});
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(20, 39, 146, 23);
		contentPane.add(btnNewButton);
		
//Creates the labels	
		JLabel lblCurrentPatients = new JLabel("Current Patients");
		lblCurrentPatients.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCurrentPatients.setBounds(20, 341, 122, 14);
		contentPane.add(lblCurrentPatients);
		
		txtfirstName = new JTextField();
		txtfirstName.setColumns(10);
		txtfirstName.setBounds(169, 379, 230, 20);
		contentPane.add(txtfirstName);
		
		txtlastName = new JTextField();
		txtlastName.setColumns(10);
		txtlastName.setBounds(169, 416, 230, 20);
		contentPane.add(txtlastName);
		
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(169, 451, 230, 20);
		contentPane.add(txtEmail);
		
		txtssn = new JTextField();
		txtssn.setColumns(10);
		txtssn.setBounds(169, 486, 230, 20);
		contentPane.add(txtssn);
		
		txtdob = new JTextField();
		txtdob.setColumns(10);
		txtdob.setBounds(169, 521, 230, 20);
		contentPane.add(txtdob);


		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(225, 550, 162, 23);
		
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(412, 550, 162, 23);
		
		String col[] = {"Patient Id", "First Name", "Last Name", "Email", "SSN", "Birthdate"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);		

//Adds the combo box
		try {
			
			JComboBox<String> comboBox = new JComboBox<String>();
			
			comboBox.setBounds(169, 340, 230, 20);
			SchedulerData schedule = SchedulerXMLReaderUtils.readSchedulerData(INPUT_FILE);
				//comboBox.addItem("Patient");
			for (Patient patients : schedule.p){
				//if (patients.getPatientID() = null){
				comboBox.addItem(patients.getFName() + " "+ patients.getLName());
				
			}
			
//Gets selected item in combo box
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				String s = (String) comboBox.getSelectedItem();
				
// Loops through list of patients
				for (Patient patients:schedule.p)
					if (s.equals(patients.getFName()+ " "+ patients.getLName())){

//Date Formaatter
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // ignore time zones for simplicity
						String dateStr = df.format(patients.getDob());
						
// Fills the text fields with desired name
						txtfirstName.setText(patients.getFName());
						txtlastName.setText(patients.getLName());
						txtEmail.setText(patients.getEmail());
						txtssn.setText(patients.getSsn());
						txtdob.setText(dateStr);
						
//Update Patient Info (only updates the first name)
						btnUpdate.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								for (int k = 0; k<schedule.getPatientList().size(); k++){
								if(comboBox.getSelectedItem().equals("Patient"))
								{
								txtfirstName.setText(schedule.getPatientList().get(k).getFName());
				
								}
							
								
								try {
									SchedulerWriterUtils.writeSchedulerData(INPUT_FILE, schedule);
									JOptionPane.showMessageDialog(null, "Successfully updated a Patient.");
									tableModel.fireTableDataChanged();
								} catch (XMLStreamException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							}
						});
						}
						
					}
					}
				
				);
			contentPane.add(comboBox);
			contentPane.add(btnUpdate);
			
			
//Fills table with patient's info
				for (Patient patients: schedule.p){
					if(!patients.getSsn().equals(null)){
						{
						int patientID = patients.getPatientID();
						String fname = patients.getFName();
						String lname = patients.getLName();
						String email = patients.getEmail();
						String ssn = patients.getSsn();
//Date Formatter
						DateFormat df = new SimpleDateFormat("MMM-dd-YYYY"); // ignore time zones for simplicity
						String dateStr = df.format(patients.getDob());
						
						Object[] data = {patientID , fname, lname, email, ssn, dateStr};
						tableModel.addRow(data);
						}
					}
				}
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(25, 93, 500, 237);
			contentPane.add(scrollPane);
			JTable table_1 = new JTable(tableModel);
			table_1.setBounds(25, 93, 500, 237);
			scrollPane.setViewportView(table_1);
			
			JLabel label = new JLabel("First Name");
			label.setFont(new Font("Tahoma", Font.BOLD, 12));
			label.setBounds(20, 381, 78, 14);
			contentPane.add(label);
			
			JLabel label_1 = new JLabel("Last Name");
			label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
			label_1.setBounds(20, 418, 78, 14);
			contentPane.add(label_1);
			
			JLabel label_2 = new JLabel("Email");
			label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
			label_2.setBounds(20, 453, 78, 14);
			contentPane.add(label_2);
			
			
			JLabel label_3 = new JLabel("SSN");
			label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
			label_3.setBounds(20, 488, 95, 14);
			contentPane.add(label_3);
			
			JLabel label_4 = new JLabel("Date of Birth");
			label_4.setFont(new Font("Tahoma", Font.BOLD, 12));
			label_4.setBounds(20, 523, 95, 14);
			contentPane.add(label_4);
			

			


			
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}


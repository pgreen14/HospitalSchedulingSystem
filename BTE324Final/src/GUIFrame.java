import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * 
 *
 */
public class GUIFrame {

	private JFrame frame;
	//private final static String INPUT_FILE = "/Users/perigreen/HospitalSchedulingSystem/BTE324Final/resources/schedulerDataIn.xml";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					GUIFrame window = new GUIFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		
		JLabel lblPatientSchedulingSystem = new JLabel("Patient Scheduling System");
		lblPatientSchedulingSystem.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPatientSchedulingSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblPatientSchedulingSystem.setBounds(89, 11, 261, 23);
		frame.getContentPane().add(lblPatientSchedulingSystem);
		
		
		JButton btnScheduleVisit = new JButton("Schedule Visit");
		btnScheduleVisit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisitInfoFrame newFrame = new VisitInfoFrame();
				newFrame.setVisible(true);
			
			}
		});
		btnScheduleVisit.setForeground(Color.BLACK);
		btnScheduleVisit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnScheduleVisit.setBounds(99, 56, 251, 23);
		frame.getContentPane().add(btnScheduleVisit);
		
		JButton btnPatients = new JButton("Patient Information (Click this)");
		btnPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PatientInfoFrame newFrame = new PatientInfoFrame();
				newFrame.setVisible(true);
			}
		});
		btnPatients.setForeground(Color.BLACK);
		btnPatients.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPatients.setBounds(99, 105, 251, 23);
		frame.getContentPane().add(btnPatients);
		
		JButton btnNewDoctor = new JButton("Doctor Information");
		btnNewDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DoctorInfoFrame newFrame = new DoctorInfoFrame();
				newFrame.setVisible(true);
				
			}
		});
		btnNewDoctor.setForeground(Color.BLACK);
		btnNewDoctor.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewDoctor.setBounds(99, 150, 251, 23);
		frame.getContentPane().add(btnNewDoctor);
	}
}

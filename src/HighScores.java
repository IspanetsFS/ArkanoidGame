import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.*;
import javax.swing.*;
import javax.swing.RowSorter.SortKey;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

class MyTableModel extends DefaultTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Class<?> getColumnClass(int columnIndex){
		if (columnIndex == 3) {
			return Integer.class;
		}else
			return String.class;
	}
	
}

public class HighScores extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] difficulty = { "Легкий", "Средний", "Сложный" };
	private String[] modes = { "Кампания", "Бесконечный" };
	private static String choose = "Кампания";
	private JComboBox<String> difficultyBox = new JComboBox<String>(difficulty);
	private JComboBox<String> modesBox = new JComboBox<String>(modes);
	private JLabel labelDifficulty = new JLabel("Сложность: ");
	private JLabel labelModes = new JLabel("Режимы: ");
	public JButton exit = new JButton();
	private ArrayList<Object[]> list = new ArrayList<Object[]>();
	
	
	
	private static DefaultTableModel dm = new MyTableModel();
	private static JTable tableScores = new JTable(dm);
	private JScrollPane scrollPane = new JScrollPane();

	public HighScores() {

		this.setLayout(null);
		this.setBackground(Color.LIGHT_GRAY);

		labelDifficulty.setBounds(5, 5, 80, 20);
		labelModes.setBounds(200, 5, 80, 20);

		difficultyBox.setBounds(80, 5, 100, 20);

		modesBox.setBounds(260, 5, 100, 20);

		scrollPane.setBounds(5, 40, 790, 550);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		
		exit.setLocation(708, 0);
		exit.setSize(82, 30);
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		try {
			ImageIcon icon = new ImageIcon("resources\\ArkanoidMini.png");
			exit.setIcon(icon);
		}catch(Exception e) {
			e.printStackTrace();
		}
		this.add(exit);

		this.add(labelDifficulty);
		this.add(labelModes);
		this.add(difficultyBox);
		this.add(modesBox);
		this.add(scrollPane);
		scrollPane.getViewport().add(tableScores);
		dm.addColumn("Имя игрока");
		dm.addColumn("Дата");
		dm.addColumn("Пройдено уровней");
		dm.addColumn("Очки");
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dm);
		sorter.setSortable(0, false);
		sorter.setSortable(1, false);
		sorter.setSortable(2, false);
		sorter.setSortable(3, true);
		ArrayList<SortKey> keys = new ArrayList<SortKey>();
		keys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
		keys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
		
		sorter.setSortKeys(keys);
		sorter.toggleSortOrder(3);
		sorter.setSortsOnUpdates(true);
		
		
		tableScores.setRowSorter(sorter);
		
		modesBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<?> box = (JComboBox<?>) e.getSource();
				choose = (String) box.getSelectedItem();

			}
		});

		difficultyBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<?> box = (JComboBox<?>) e.getSource();
				String item = (String) box.getSelectedItem();
				if (choose.equals("Кампания")) {
					switch (item) {
					case "Легкий":
						removeRows();
						fillInfo("campaignEasy.dat");
						break;
					case "Средний":
						removeRows();
						fillInfo("campaignMedium.dat");
						break;
					case "Сложный":
						removeRows();
						fillInfo("campaignHard.dat");
						break;
					default:
						break;
					}
				}else {
					switch (item) {
					case "Легкий":
						removeRows();
						fillInfo("endlessEasy.dat");
						break;
					case "Средний":
						removeRows();
						fillInfo("endlessMedium.dat");
						break;
					case "Сложный":
						removeRows();
						fillInfo("endlessHard.dat");
						break;
					default:
						break;
					}
				}
			}
		});

	}

	public void fillInfo(String file) {
		try (InputStream in = new FileInputStream("resources\\scores\\" + file);
				BufferedInputStream buf = new BufferedInputStream(in);
				DataInputStream data = new DataInputStream(buf);) {

			int i = 0;
			while (data.available() > 1) {
				list.add(new Object[4]);
				list.get(i)[0] = DataInputStream.readUTF(data);
				list.get(i)[1] = DataInputStream.readUTF(data);
				list.get(i)[2] = data.readInt();
				list.get(i)[3] = data.readInt();
				
				dm.fireTableDataChanged();
				dm.addRow(list.get(i));
				i++;
				
			}

			/*Object[][] obj = new Object[list.size()][4];

			for (int j = 0; j < list.size(); j++) {
				obj[j][0] = list.get(j)[0];
				obj[j][1] = list.get(j)[1];
				obj[j][2] = list.get(j)[2];
				obj[j][3] = list.get(j)[3];
			}*/

			
			
			setingsTable();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void removeRows() {
		for(int i = tableScores.getRowCount() - 1; i >= 0; i--) {
			dm.fireTableDataChanged();
			dm.removeRow(i);
		}
	}
	
	private void setingsTable() {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tableScores.setFont(new Font("TimesRoman", Font.BOLD, 15));
		tableScores.getTableHeader().setFont(new Font("Italic", Font.BOLD, 14));
		for (int i = 0; i < tableScores.getColumnCount(); i++) {
			tableScores.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		for (int row = 0; row < tableScores.getRowCount(); row++) {
			int rowHeight = tableScores.getRowHeight();

			for (int column = 0; column < tableScores.getColumnCount(); column++) {
				Component comp = tableScores.prepareRenderer(tableScores.getCellRenderer(row, column), row, column);
				rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
			}

			tableScores.setRowHeight(row, rowHeight);
		}
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawLine(0, 30, 806, 30);
		g.drawLine(0, 32, 806, 32);
	}

}

package com.otel.view;

import com.otel.database.RezervasyonDB;
import com.otel.helper.SessionManager;
import com.otel.model.Rezervasyon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GecmisKonaklamalarFrame extends BaseMainFrame {

    private int musteriId;
    private JTable historyTable;
    private DefaultTableModel tableModel;
    private JLabel toplamKonaklamaLabel;
    private JLabel toplamHarcamaLabel;

    public GecmisKonaklamalarFrame() {
        super();
        this.musteriId = SessionManager.getInstance().getUserId();
        initContent();
        konaklamalariYukle();
    }

    public GecmisKonaklamalarFrame(int hedefMusteriId) {
        super();
        this.musteriId = hedefMusteriId;



        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Müşteri Geçmiş Konaklamaları (ID: " + musteriId + ")");

        initContent();
        konaklamalariYukle();


    }

    @Override
    protected void initContent() {
        contentPanel.setLayout(new BorderLayout(10, 10));
        contentPanel.setBackground(Color.WHITE);

        JPanel ustPanel = new JPanel(new BorderLayout());
        ustPanel.setBackground(Color.WHITE);
        ustPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel baslikLabel = new JLabel("KONAKLAMA GEÇMİŞİM");
        baslikLabel.setFont(new Font("Arial", Font.BOLD, 20));
        ustPanel.add(baslikLabel, BorderLayout.WEST);

        contentPanel.add(ustPanel, BorderLayout.NORTH);

        JPanel centerContainer = new JPanel(new BorderLayout(0, 10));
        centerContainer.setBackground(Color.WHITE);

        JPanel istatistikPanel = createIstatistikPanel();
        centerContainer.add(istatistikPanel, BorderLayout.NORTH);

        String[] columnNames = {"Rezervasyon No", "Oda No", "Oda Tipi", "Giriş Tarihi",
                "Çıkış Tarihi", "Gün", "Kişi", "Toplam Ücret", "Detay"};

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 8;
            }
        };

        historyTable = new JTable(tableModel);
        historyTable.setRowHeight(30);
        historyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        historyTable.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer());
        historyTable.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(historyTable);
        centerContainer.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(centerContainer, BorderLayout.CENTER);

        JPanel altPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        altPanel.setBackground(Color.WHITE);
        altPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel bilgiLabel = new JLabel("💡 Sadece süresi dolmuş ve tamamlanmış konaklamalarınız listelenir.");
        bilgiLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        bilgiLabel.setForeground(Color.GRAY);
        altPanel.add(bilgiLabel);

        contentPanel.add(altPanel, BorderLayout.SOUTH);
    }

    private JPanel createIstatistikPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 20, 0));
        panel.setBackground(new Color(240, 248, 255));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createLineBorder(new Color(200, 200, 200))
        ));

        JPanel konaklamaPanel = new JPanel(new BorderLayout());
        konaklamaPanel.setBackground(new Color(240, 248, 255));
        konaklamaPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel konaklamaBaslik = new JLabel("🏨 Toplam Konaklama");
        konaklamaBaslik.setFont(new Font("Arial", Font.PLAIN, 14));
        konaklamaPanel.add(konaklamaBaslik, BorderLayout.NORTH);

        toplamKonaklamaLabel = new JLabel("0 Adet");
        toplamKonaklamaLabel.setFont(new Font("Arial", Font.BOLD, 24));
        toplamKonaklamaLabel.setForeground(new Color(51, 102, 153));
        konaklamaPanel.add(toplamKonaklamaLabel, BorderLayout.CENTER);

        panel.add(konaklamaPanel);

        JPanel harcamaPanel = new JPanel(new BorderLayout());
        harcamaPanel.setBackground(new Color(240, 248, 255));
        harcamaPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel harcamaBaslik = new JLabel("Toplam Harcama");
        harcamaBaslik.setFont(new Font("Arial", Font.PLAIN, 14));
        harcamaPanel.add(harcamaBaslik, BorderLayout.NORTH);

        toplamHarcamaLabel = new JLabel("0.00 TL");
        toplamHarcamaLabel.setFont(new Font("Arial", Font.BOLD, 24));
        toplamHarcamaLabel.setForeground(new Color(51, 153, 102));
        harcamaPanel.add(toplamHarcamaLabel, BorderLayout.CENTER);

        panel.add(harcamaPanel);

        return panel;
    }

    private void konaklamalariYukle() {
        tableModel.setRowCount(0);
        RezervasyonDB db = new RezervasyonDB();
        List<Rezervasyon> konaklamalar = db.gecmisMusteriKonaklama(musteriId);

        double toplamHarcama = 0;

        for (Rezervasyon rez : konaklamalar) {
            String odaNumarasi = rez.getOda() != null ? rez.getOda().getOdaNumarasi() : "-";
            String odaTipi = rez.getOda() != null ? rez.getOda().getOdaTipi() : "-";

            Object[] row = {
                    rez.getId(),
                    odaNumarasi,
                    odaTipi,
                    rez.getGirisTarihi(),
                    rez.getCikisTarihi(),
                    rez.getKonaklamaSüresi() + " gün",
                    rez.getKisiSayisi(),
                    String.format("%.2f TL", rez.getToplamFiyat()),
                    "Detay"
            };
            tableModel.addRow(row);
            toplamHarcama += rez.getToplamFiyat();
        }

        toplamKonaklamaLabel.setText(konaklamalar.size() + " Adet");
        toplamHarcamaLabel.setText(String.format("%.2f TL", toplamHarcama));
    }

    private void rezervasyonDetay(int row) {
        int rezId = (Integer) tableModel.getValueAt(row, 0);
        RezervasyonDB db = new RezervasyonDB();
        Rezervasyon rez = db.rezervasyonGetirDetayli(rezId);

        if (rez == null) return;

        String detay = String.format(
                "DETAYLAR\n\n" +
                        "Rezervasyon No: %d\n" +
                        "Oda: %s (%s)\n" +
                        "Giriş: %s\n" +
                        "Çıkış: %s\n" +
                        "Süre: %d gün\n" +
                        "Tutar: %.2f TL\n" +
                        "Durum: TAMAMLANDI\n",
                rez.getId(),
                rez.getOda() != null ? rez.getOda().getOdaNumarasi() : "-",
                rez.getOda() != null ? rez.getOda().getOdaTipi() : "-",
                rez.getGirisTarihi(), rez.getCikisTarihi(),
                rez.getKonaklamaSüresi(), rez.getToplamFiyat()
        );

        JTextArea area = new JTextArea(detay);
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JOptionPane.showMessageDialog(this, new JScrollPane(area), "Detay", JOptionPane.INFORMATION_MESSAGE);
    }

    class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setBackground(new Color(51, 102, 153));
            setForeground(Color.WHITE);
        }
        public Component getTableCellRendererComponent(JTable t, Object v, boolean isSel, boolean hasFoc, int r, int c) {
            setText((v == null) ? "" : v.toString());
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean isPushed;
        private int selectedRow;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.setBackground(new Color(51, 102, 153));
            button.setForeground(Color.WHITE);
            button.addActionListener(e -> fireEditingStopped());
        }
        public Component getTableCellEditorComponent(JTable t, Object v, boolean isSel, int r, int c) {
            label = (v == null) ? "" : v.toString();
            button.setText(label);
            isPushed = true;
            selectedRow = r;
            return button;
        }
        public Object getCellEditorValue() {
            if (isPushed) rezervasyonDetay(selectedRow);
            isPushed = false;
            return label;
        }
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }
}
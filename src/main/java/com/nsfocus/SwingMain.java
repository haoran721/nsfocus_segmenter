package com.nsfocus;

import com.nsfocus.pojo.InfoModel;
import com.nsfocus.util.ExcelUtil;
import com.nsfocus.util.SegmentUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * @Description
 * @Author wanghui
 * @Date 2019/1/26 13:50
 **/
public class SwingMain {
    private static Logger logger = LoggerFactory.getLogger(SwingMain.class);

    /**
     * {
     * 创建并显示GUI。出于线程安全的考虑，
     * 这个方法在事件调用线程中调用。
     */
    public static void createAndShowGUI() {
        //异步预热分词框架
        Executors.newSingleThreadExecutor().execute(() -> logger.info("【】", StringUtils.join(SegmentUtil.segment("预热分词框架"), " ")));

        // 创建及设置窗口
        JFrame frame = new JFrame("NSFOCUS-收货地址分割工具");
        int width = 500;
        int height = 300;
        int w = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
        int h = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
        frame.setLocation(w, h);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        Container container = frame.getContentPane();

        JButton button = new JButton("打开...");
        button.setHideActionText(true);
        button.setBorderPainted(true);
//        button.setMaximumSize(new Dimension(90, 30));//设置按钮和图片的大小相同
        button.setPreferredSize(new Dimension(90, 30));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = chooseFile();
                //弹出对话框
                JOptionPane.showMessageDialog(null, message);
            }
        });
        container.add(button);

        // 显示窗口
        frame.setVisible(true);
    }

    private static String chooseFile() {
        JFileChooser jfc = new JFileChooser();
        String outFilepath = "_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xlsx";

        String message = "";

        try {
            if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = jfc.getSelectedFile();
                //校验是否为xlsx文件
                FileInputStream inputStream = new FileInputStream(file);
                if (file.getName().endsWith(".xlsx")) {
                    JOptionPane.showMessageDialog(null, "正在处理,请稍候...");
                    //获取绝对路径
                    String absolutePath = file.getAbsolutePath();
                    outFilepath = absolutePath.replaceAll("\\.xlsx", outFilepath);
                    List<InfoModel> infoModels = ExcelUtil.readExcel(inputStream);
                    ProcessInfo.process(infoModels);
                    ExcelUtil.writeExcel(outFilepath, infoModels);
                    message = "Excel处理完毕  文件保存到:" + outFilepath;
                } else {
                    message = "文件格式不正确！请选择xlsx格式文件！";
                }
            } else {
                message = "没有选择文件！";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public static void main(String[] args) {
        // 显示应用 GUI
//        javax.swing.SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
//        });
    }
}

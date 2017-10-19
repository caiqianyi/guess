package com.caiqianyi.commons.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

/**
 * 实现描述:二维码生成和解析工具类
 *
 * @author carreerly
 * @version v1.0.0
 * @see
 * @since 15/10/08 下午2:13
 */
public class QRCodeUtils {

    private static Logger logger = LoggerFactory.getLogger(QRCodeUtils.class);

    private static final String CHARSET = "UTF-8";

    private static final String IMG_FORMAT_NAME = "jpg";

    // LOGO宽度
    private static final int LOG_WIDTH = 60;
    // LOGO高度
    private static final int LOG_HEIGHT = 60;

    /**
     * 创建临时文件，JVM关闭时删除文件
     */
    private static File createTempFile(String suffix) throws IOException {
        final File tempFile = File.createTempFile("temp", suffix, null);
        tempFile.deleteOnExit();
        return tempFile;
    }

    /**
     * 生成普通二维码
     *
     * @param contents
     * @param width
     * @param height
     */
    public static File encodePR(String contents, int width, int height) {
        Map<EncodeHintType, Object> hints = new HashMap();
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.MARGIN, 1);
        // 指定编码格式
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        try {
            File tempFile = createTempFile(UUID.randomUUID().toString() + ".jpg");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, IMG_FORMAT_NAME, new FileOutputStream(tempFile));
            return tempFile;
        } catch (Exception e) {
            logger.error(String.format("encodePR error contents:%s", contents), e);
            return null;
        }
    }

    /**
     * 生成带图片的二维码
     *
     * @param content      内容
     * @param needCompress 是否压缩LOGO
     * @throws Exception
     */
    public static File encodePR(String content, File imgFile,
                                boolean needCompress, int width, int height) throws Exception {
        BufferedImage bufferedImage = createImage(content, width, height, imgFile, needCompress);
        File tempFile = createTempFile(UUID.randomUUID().toString() + ".jpg");
        ImageIO.write(bufferedImage, IMG_FORMAT_NAME, tempFile);
        return tempFile;
    }


    private static BufferedImage createImage(String content, int width, int height, File imgFile,
                                             boolean needCompress) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, width, height, hints);
        int bitWidth = bitMatrix.getWidth();
        int bitHeight = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(bitWidth, bitHeight,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < bitWidth; x++) {
            for (int y = 0; y < bitHeight; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
                        : 0xFFFFFFFF);
            }
        }
        if (image == null) {
            return image;
        }
        // 插入logo
        if (imgFile != null) {
            insertLogo(image, width, height, imgFile, needCompress);
        }
        return image;
    }


    /**
     * 插入LOGO
     *
     * @param source       二维码图片
     * @param imgFile      LOGO图片
     * @param needCompress 是否压缩
     * @throws Exception
     */
    private static void insertLogo(BufferedImage source, int width, int height, File imgFile,
                                   boolean needCompress) throws Exception {
        Image src = ImageIO.read(imgFile);
        int imgWidth = src.getWidth(null);
        int imgHeight = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (imgWidth > LOG_WIDTH) {
                imgWidth = LOG_WIDTH;
            }
            if (imgHeight > LOG_HEIGHT) {
                imgHeight = LOG_HEIGHT;
            }
            Image image = src.getScaledInstance(imgWidth, imgHeight,
                    Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(imgWidth, imgHeight,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (width - imgWidth) / 2;
        int y = (height - imgHeight) / 2;
        graph.drawImage(src, x, y, imgWidth, imgHeight, null);
        Shape shape = new RoundRectangle2D.Float(x, y, imgWidth, imgWidth, 10, 10);
        graph.setStroke(new BasicStroke(0f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     * 解密二维码
     *
     * @param url
     * @return
     */
    public static String decryptPR(String url) {
        BufferedImage bufferedImage = null;
        try {
            URL httpUrl = new URL(url);
            bufferedImage = ImageIO.read(httpUrl.openStream());
        } catch (IOException e) {
            logger.error(String.format("read img:%s error", url), e);
            return null;
        }
        return decryptPR(bufferedImage);

    }

    /**
     * 解析二维码
     *
     * @param ins
     * @return
     */
    public static String decryptPR(InputStream ins) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(ins);
        } catch (IOException e) {
            logger.error("read error!", e);
            return null;
        }
        return decryptPR(bufferedImage);

    }

    /**
     * 解析二维码
     *
     * @param bufferedImage
     * @return
     */
    private static String decryptPR(BufferedImage bufferedImage) {
        LuminanceSource lus = null;
        lus = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bm = new BinaryBitmap(new HybridBinarizer(lus));
        Map<DecodeHintType, String> hints = new HashMap();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        try {
            Result result = new MultiFormatReader().decode(bm, hints);
            return result.getText();
        } catch (NotFoundException e) {
            logger.error("read error", e);
            return null;
        }
    }


}

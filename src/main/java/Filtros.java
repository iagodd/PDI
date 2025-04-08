import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Filtros {

    public void aplicaGrayscale(JLabel labelOriginal, JLabel labelTransformado) {
        if (labelOriginal.getIcon() != null) {
            ImageIcon originalIcon = (ImageIcon) labelOriginal.getIcon();
            Image img = originalIcon.getImage();
    
            BufferedImage bufferedImage = new BufferedImage(
                img.getWidth(null),
                img.getHeight(null),
                BufferedImage.TYPE_INT_ARGB
            );
            Graphics2D g2d = bufferedImage.createGraphics();
            g2d.drawImage(img, 0, 0, null);
            g2d.dispose();
    
            int largura = bufferedImage.getWidth();
            int altura = bufferedImage.getHeight();
    
            BufferedImage novaImagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);
    
            for (int y = 0; y < altura; y++) {
                for (int x = 0; x < largura; x++) {
                    int pixel = bufferedImage.getRGB(x, y);
    
                    int alpha = (pixel >> 24) & 0xff;
                    int red   = (pixel >> 16) & 0xff;
                    int green = (pixel >> 8) & 0xff;
                    int blue  = pixel & 0xff;
    
                    int cinza = (int)(0.3 * red + 0.59 * green + 0.11 * blue);
    
                    int novoPixel = (alpha << 24) | (cinza << 16) | (cinza << 8) | cinza;
                    novaImagem.setRGB(x, y, novoPixel);
                }
            }
    
            labelTransformado.setIcon(new ImageIcon(novaImagem));
        }
        else
            JOptionPane.showMessageDialog(null, "Por favor, insira uma imagem primeiro", "Erro", JOptionPane.ERROR_MESSAGE);
    }
    

    public void passaBaixa(JLabel labelOriginal, JLabel labelTransformado) {
        if (labelOriginal.getIcon() != null) {
            ImageIcon originalIcon = (ImageIcon) labelOriginal.getIcon();
            Image img = originalIcon.getImage();
    
            BufferedImage bufferedImage = new BufferedImage(
                img.getWidth(null),
                img.getHeight(null),
                BufferedImage.TYPE_INT_ARGB
            );
            Graphics2D g2d = bufferedImage.createGraphics();
            g2d.drawImage(img, 0, 0, null);
            g2d.dispose();
    
            int largura = bufferedImage.getWidth();
            int altura = bufferedImage.getHeight();
    
            BufferedImage novaImagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);
    
    
            for (int y = 1; y < altura - 1; y++) {
                for (int x = 1; x < largura - 1; x++) {
                    int somaR = 0, somaG = 0, somaB = 0;

                    for (int j = -1; j <= 1; j++) {
                        for (int i = -1; i <= 1; i++) {
                            int pixel = bufferedImage.getRGB(x + i, y + j);
    
                            int red   = (pixel >> 16) & 0xff;
                            int green = (pixel >> 8) & 0xff;
                            int blue  = pixel & 0xff;
    
                            somaR += red;
                            somaG += green;
                            somaB += blue;
                        }
                    }
    
                    int mediaR = somaR / 9;
                    int mediaG = somaG / 9;
                    int mediaB = somaB / 9;

                    int alpha = (bufferedImage.getRGB(x, y) >> 24) & 0xff;
                    int novoPixel = (alpha << 24) | (mediaR << 16) | (mediaG << 8) | mediaB;
    
                    novaImagem.setRGB(x, y, novoPixel);
                }
            }
            labelTransformado.setIcon(new ImageIcon(novaImagem));
        }
        else
            JOptionPane.showMessageDialog(null, "Por favor, insira uma imagem primeiro", "Erro", JOptionPane.ERROR_MESSAGE);
    }
    
    public void passaAlta(JLabel labelOriginal, JLabel labelTransformado) {
        if (labelOriginal.getIcon() != null) {
            ImageIcon originalIcon = (ImageIcon) labelOriginal.getIcon();
            Image img = originalIcon.getImage();
    
            BufferedImage bufferedImage = new BufferedImage(
                img.getWidth(null),
                img.getHeight(null),
                BufferedImage.TYPE_INT_ARGB
            );
            Graphics2D g2d = bufferedImage.createGraphics();
            g2d.drawImage(img, 0, 0, null);
            g2d.dispose();
    
            int largura = bufferedImage.getWidth();
            int altura = bufferedImage.getHeight();
    
            BufferedImage novaImagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);
    
            int[][] mascara = {
                {-1, -1, -1},
                {-1,  8, -1},
                {-1, -1, -1}
            };
    
            for (int y = 1; y < altura - 1; y++) {
                for (int x = 1; x < largura - 1; x++) {
                    int somaR = 0, somaG = 0, somaB = 0;
    
                    for (int j = -1; j <= 1; j++) {
                        for (int i = -1; i <= 1; i++) {
                            int pixel = bufferedImage.getRGB(x + i, y + j);
    
                            int red   = (pixel >> 16) & 0xff;
                            int green = (pixel >> 8) & 0xff;
                            int blue  = pixel & 0xff;
    
                            int peso = mascara[j + 1][i + 1];
    
                            somaR += red * peso;
                            somaG += green * peso;
                            somaB += blue * peso;
                        }
                    }
    
                    somaR = Math.min(255, Math.max(0, somaR));
                    somaG = Math.min(255, Math.max(0, somaG));
                    somaB = Math.min(255, Math.max(0, somaB));
    
                    int alpha = (bufferedImage.getRGB(x, y) >> 24) & 0xff;
                    int novoPixel = (alpha << 24) | (somaR << 16) | (somaG << 8) | somaB;
    
                    novaImagem.setRGB(x, y, novoPixel);
                }
            }
    
            labelTransformado.setIcon(new ImageIcon(novaImagem));
        }
        else
        JOptionPane.showMessageDialog(null, "Por favor, insira uma imagem primeiro", "Erro", JOptionPane.ERROR_MESSAGE);
    }

}
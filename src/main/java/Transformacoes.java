import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

    
     public class Transformacoes {

    public void transladarImagem(JLabel labelOriginal, JLabel labelTransformado) {
        if (labelOriginal.getIcon() != null) {
            ImageIcon originalIcon = (ImageIcon) labelOriginal.getIcon();
            Image img = originalIcon.getImage();
            int largura = img.getWidth(null);
            int altura = img.getHeight(null);

            BufferedImage bufferedImage = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);
            int[] pixels = new int[largura * altura];
            bufferedImage.getRGB(0, 0, largura, altura, pixels, 0, largura);
            int tx = 50;
            int ty = 50;

            BufferedImage novaImagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);

            for (int y = 0; y < altura; y++) {
                for (int x = 0; x < largura; x++) {
                    int novoX = x + tx;
                    int novoY = y + ty;
                    if (novoX >= 0 && novoX < largura && novoY >= 0 && novoY < altura) {
                        novaImagem.setRGB(novoX, novoY, pixels[y * largura + x]);
                    }
                }
            }

            labelTransformado.setIcon(new ImageIcon(novaImagem));
        }
    }
}


package Utils.Metodos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;

import Utils.Objetos.ValidCEP;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class MetodosUtils extends ValidCEP {

    public static ValidCEP ResponseCEP(String NumCEP, int NumEndereco) {

        ValidCEP novoCEP = new ValidCEP();

        try {
            String url = "https://viacep.com.br/ws/" + NumCEP + "/xml/";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));//"ISO-8859-1"));
            StringBuffer response = new StringBuffer();
            try {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine.replace('\u00A0',' '));
                }
                in.close();
                }
            catch (IOException e) {
                throw new RuntimeException(e);
            }

            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(response.toString())));
            NodeList errNodes = doc.getElementsByTagName("xmlcep");
            if (errNodes.getLength() > 0) {
                Element err = (Element) errNodes.item(0);
                novoCEP.setCEP((NumCEP).strip());
                novoCEP.setEndereco((err.getElementsByTagName("logradouro").item(0).getTextContent()).strip());
                novoCEP.setBairro((err.getElementsByTagName("bairro").item(0).getTextContent()).strip());
                novoCEP.setNum(NumEndereco);
                novoCEP.setCidade((err.getElementsByTagName("localidade").item(0).getTextContent()).strip());
                novoCEP.setUF((err.getElementsByTagName("uf").item(0).getTextContent()).strip());
            } else {
                System.out.printf("Não foi possível retornar com os dados do CEP: %s informado", NumCEP);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return novoCEP;
    }
}

//#region notes
/*
                System.out.println("tipo_logradouro - " + err.getElementsByTagName("tipo_logradouro").item(0).getTextContent());
                System.out.println("logradouro - " + err.getElementsByTagName("logradouro").item(0).getTextContent());
                System.out.println("bairro - " + err.getElementsByTagName("bairro").item(0).getTextContent());
                System.out.println("cidade - " + err.getElementsByTagName("cidade").item(0).getTextContent());
                System.out.println("uf - " + err.getElementsByTagName("uf").item(0).getTextContent());


webservice de cep - https://viacep.com.br/

            //String url = "http://cep.republicavirtual.com.br/web_cep.php?cep=" + NumCEP + "&formato=xml";

                            NodeList errNodes = doc.getElementsByTagName("webservicecep");
            if (errNodes.getLength() > 0) {
                Element err = (Element) errNodes.item(0);
                novoCEP.setCEP((NumCEP).strip());
                novoCEP.setEndereco((err.getElementsByTagName("tipo_logradouro").item(0).getTextContent() + " "
                        + err.getElementsByTagName("logradouro").item(0).getTextContent()).strip());
                novoCEP.setBairro((err.getElementsByTagName("bairro").item(0).getTextContent()).strip());
                novoCEP.setNum(NumEndereco);
                novoCEP.setCidade((err.getElementsByTagName("cidade").item(0).getTextContent()).strip());
                novoCEP.setUF((err.getElementsByTagName("uf").item(0).getTextContent()).strip());
*/
//#endregion
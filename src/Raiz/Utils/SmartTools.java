package Raiz.Utils;

import java.nio.charset.StandardCharsets;
import java.time.*;
import java.util.*;

import Cadastro.NovosDados.Repositorio.Auxiliar.Criptografia;
import Cadastro.NovosDados.Repositorio.Enums.agora;
import Cadastro.NovosDados.Repositorio.Enums.operacao;
import Raiz.Utils.leitorDados;

public abstract class smartTools {

    public static class Senha {

        /**
         * Efetua a criptografia da senha recebida via parâmetro
         */
        public static Criptografia Encrypt(String password) {
            Base64.Encoder encrypt = Base64.getEncoder();
            byte[] request = (password.strip()).getBytes(StandardCharsets.ISO_8859_1);
            String encoded = encrypt.encodeToString(request);
            //StringBuilder Char_byte = new StringBuilder(CharLength(encoded, Operation.ENCRYPT));
            StringBuilder Char_byte = new StringBuilder(charLength(encoded, operacao.ENCRYPT));

            String HEX = asciiToHex(new String(Char_byte));

            Base64.Encoder encryptHEX = Base64.getEncoder();
            byte[] requestHEX = (HEX.strip()).getBytes(StandardCharsets.ISO_8859_1);
            String encodedHEX = encryptHEX.encodeToString(requestHEX);
            Criptografia hashcode = new Criptografia();
            hashcode.setEncrypting(encodedHEX);

            return hashcode;
        }

        /**
         * Reverte a criptografia da senha recebida via parâmetro
         */
        private static String Decrypt(String hex) {
            Base64.Decoder decryptHEX = Base64.getDecoder();
            byte[] responseHEX = decryptHEX.decode(new String(hex));
            String decodedHEX = new String(responseHEX, StandardCharsets.ISO_8859_1);
            //--
            Base64.Decoder decrypt = Base64.getDecoder();
            String password = hexToASCII(decodedHEX);
            StringBuilder Char_byte = new StringBuilder(charLength(password, operacao.DECRYPT));

            byte[] response = decrypt.decode(new String(Char_byte));
            String decoded;
            decoded = new String(response, StandardCharsets.ISO_8859_1);

            return decoded;
        }

        /**
         * Efetua a conversão de ascii para hexadecimal via parâmetro
         */
        private static String asciiToHex(String asciiValue) {

            StringBuilder hex = new StringBuilder();

            char[] chars = asciiValue.toCharArray();
            for (char aChar : chars) {
                hex.append(Integer.toHexString(aChar));
            }

            return hex.toString();
        }

        /**
         * Efetua a conversão de ascii para hexadecimal via parâmetro
         */
        private static String hexToASCII(String hexValue) {

            StringBuilder output = new StringBuilder();

            for (int i = 0; i < hexValue.length(); i += 2) {
                String str = hexValue.substring(i, i + 2);
                output.append((char) Integer.parseInt(str, 16));
            }

            return output.toString();
        }

        /**
         * Efetua o tratamento de cada dígito da senha criptografada
         */
        private static String charLength(String password, operacao operation) {

            StringBuilder charStr = new StringBuilder();

            int MathPass = (int) Math.round(4 * 3.14159265359 * password.length()) / password.length();
            switch (operation) {
                case ENCRYPT:
                    for (int i = 0; i < password.length(); i++)
                        charStr.append((char) (password.charAt(i) - (MathPass)));
                    break;
                case DECRYPT:
                    for (int i = 0; i < password.length(); i++)
                        charStr.append((char) (password.charAt(i) + (MathPass)));
                    break;
                default:
                    break;
            }

            return new String(charStr);
        }
    }

    //#region CEP BKP
    /*
    public static class CEP {

        /**
         * Retorna o objeto CEP
         /
        public static validarCEP responseCEP(String NumCEP, int NumEndereco) {

            impressaoLog.logGenerico<CEP> printLog = new impressaoLog.logGenerico<>();
            @SuppressWarnings("unchecked") Logger log = printLog.getLogRetorno((Class<CEP>) (Object) (CEP.class));

            validarCEP novoCEP = new validarCEP();

            //StringBuffer response = getResponse(NumCEP);
            String url = "https://viacep.com.br/ws/" + NumCEP + "/xml/";
            String response = httpResponse.responseContent(url);

            try {
                assert response != null;
                Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(response)));
                NodeList errNodes = doc.getElementsByTagName("xmlcep");

                if (errNodes.getLength() > 0) {
                    Element err = (Element) errNodes.item(0);
                    novoCEP.setCEP((NumCEP).strip());

                    if (err.getElementsByTagName("complemento").item(0).getTextContent().isEmpty())
                        novoCEP.setEndereco((err.getElementsByTagName("logradouro").item(0).getTextContent()).strip());
                    else novoCEP.setEndereco((err.getElementsByTagName("logradouro").item(0).getTextContent()).strip() +
                                " (" + (err.getElementsByTagName("complemento").item(0).getTextContent()).strip() + ")");

                    novoCEP.setBairro((err.getElementsByTagName("bairro").item(0).getTextContent()).strip());
                    novoCEP.setNum(NumEndereco);
                    novoCEP.setCidade((err.getElementsByTagName("localidade").item(0).getTextContent()).strip());
                    novoCEP.setUF((err.getElementsByTagName("uf").item(0).getTextContent()).strip());
                } else System.out.printf("Não foi possível retornar com os dados do CEP: %s informado", NumCEP);
            } catch (Exception e) {
                //System.out.println(e.getMessage());
                log.warning("[" + CEP.class.getSimpleName() + "] ["+ response + "] " + e.getMessage());
            }
            return novoCEP;
        }
    }
    */
    //#endregion

    public static class DiaAtual {

        /**
         *Retorna a período do dia
         */
        public static String Daily() {

            String period = null;

            LocalTime currentDate = LocalTime.now();
            Date currentTime = Date.from(currentDate.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant());

            LocalTime midnight = LocalTime.of(0, 0, 0);
            LocalTime morning = LocalTime.of(11, 59, 59);
            LocalTime afternoon = LocalTime.of(17, 59, 59);
            LocalTime evening = LocalTime.of(23, 59, 59);

            Date dtMidnight = Date.from(midnight.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant());
            Date dtMorning = Date.from(morning.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant());
            Date dtAfternoon = Date.from(afternoon.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant());
            Date dtEvening = Date.from(evening.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant());

            if (currentTime.after(dtMidnight) && currentTime.before(dtMorning))
                //period = Current.morning.getDay();
                period = agora.morning.getDay();
            if (currentTime.after(dtMorning) && currentTime.before(dtAfternoon))
                period = agora.afternoon.getDay();
            if (currentTime.after(dtAfternoon) && currentTime.before(dtEvening))
                period = agora.evening.getDay();

            return period;
        }
    }

    public static class objetosAuxiliares extends leitorDados{

        /**
         * Efetua a validação do valor recebido via parâmetro se é ou não um número
         */
        public static boolean isNumeric(String value){

            if(value == null || value.isEmpty()) return false;
            try{
                Integer.parseInt(value);
                return true;
            }
            catch (NumberFormatException e){
                return false;
            }
        }

        /**
         * Retorna a opção selecionada no medu interno de cada área
         */
        public Integer optionMenu(String obj) {
            return readInt("\nO que deseja?" +
                    "\n\033[3m\u001B[32m(1) Permanecer na tela de cadastro de " + obj +
                    "\n(2) Retornar ao menu principal" +
                    "\n(3) Ir para o menu de cadastro geral" +
                    "\n(4) Sair da aplicação?: \u001B[0m\033[0m");
        }

    }

    public static class genericCollects {

        /**
         * Classe responsável por retornar um objeto genérico em forma de lista
         */
        public static class genericList<E> extends ArrayList<E> {

            protected List<E> list;
            public genericList() {
                this.list = new ArrayList<>();
            }

            @Override
            public String toString() {
                StringBuilder retrn = new StringBuilder();
                int k = 0;
                for (int i = 0; i < this.size(); i++) {
                    retrn.append(this.get(i));

                    while (k < i + 1 && k != this.size() - 1) {
                        retrn.append(", ");
                        k++;
                    }
                }
                return retrn.toString();
            }
        }

        /**
         * Classe responsável por retornar um objeto genérico em forma de Set
         */
        public static class genericSet<E> extends TreeSet<E> {

            protected Set<E> list;
            public genericSet() { this.list = new TreeSet<>(); } //super(l)

            @Override
            public String toString() {
                StringBuilder retrn = new StringBuilder();
                int k = 0;
                for (int i = 0; i < this.size(); i++) {
                    retrn.append(this.stream().toList().get(i));

                    while (k < i + 1 && k != this.size() - 1) {
                        retrn.append(", ");
                        k++;
                    }
                }
                return retrn.toString();
            }
        }
    }

    public static class validacaoDigitos {

        public static String message;

        /**
         * Classe responsável por validar os dígitos do CPF
         */
        public static String CPF(String doc) {
            String nine;
            nine = doc.substring(0, 9);

            String dig1 = returnDigito(calculoDigito(0, nine, 0, 10));
            String dig2 = returnDigito(calculoDigito(0, nine + dig1, 0, 11));

            return (nine + dig1 + dig2).equals(doc) ? doc : "Documento inválido.";// - " + (nine + "-" + dig1 + dig2);
        }

        /**
         * Classe responsável por validar os dígitos do CNPJ
         */
        public static String CNPJ(String doc) {
            String five, nine, six, ten;
            five = doc.substring(0, 4);
            nine = doc.substring(4, 12);
            String dig1 = returnDigito(calculoDigito(0, five, 0, 5) + calculoDigito(0, nine, 0, 9));

            six = doc.substring(0, 5);
            ten = doc.substring(5, 12) + dig1;
            String dig2 = returnDigito(calculoDigito(0, six, 0, 6) + calculoDigito(0, ten, 0, 9));

            return (five + nine + dig1 + dig2).equals(doc) ? doc : "Documento inválido.";// - " + (five + nine + dig1 + dig2);;
        }

        /**
         * Calculo para validação do documento informado
         */
        public static int calculoDigito(int soma, String id, int max, int min) {

            int dg;
            if (min != 1) {
                dg = Integer.parseInt(String.valueOf(id.charAt(max)));
                soma += dg * min;
            } else return soma;

            return calculoDigito(soma, id, max + 1, min - 1);
        }

        /**
         * Retorna os dígitos verificadores dos documentos informados
         */
        public static String returnDigito(int dig){

            int sub = dig - (11 * (dig/11));
            if (sub < 2) return "0";
            if (sub <= 10) return String.valueOf(11 - sub);
            return null;
        }
    }
}

//#region cnpj
/*
        public static void CNPJ(String doc) {

            int parteUm = 5, parteDois = 9;
            int digp1 = 0, digp2 = 0;
            String docUm = doc.substring(0, 4);
            String docDois = doc.substring(4, 12);

            for (int i = 0; i < docUm.length(); i++) {
                digp1 += Integer.parseInt(String.valueOf(docUm.charAt(i))) * parteUm;
                //System.out.println(Integer.parseInt(String.valueOf(docUm.charAt(i))) +"+"+ parteUm+ "=" +digp1);
                parteUm--;
            }

            for (int i = 0; i < docDois.length(); i++) {
                digp2 += Integer.parseInt(String.valueOf(docDois.charAt(i))) * parteDois;
                //System.out.println(Integer.parseInt(String.valueOf(docDois.charAt(i))) +"+"+ parteDois+ "=" +digp2);
                parteDois--;
            }

            int div = (digp1 + digp2)/11;
            int res = (digp1 + digp2) - (11 *div);

            String fil = null;
            if(res < 2) fil = "0";
            if(res <= 10) System.out.println(11 - res);

            //--------------

            int novaparteUm = 6, novaparteDois = 9;
            int novodigp1 = 0, novodigp2 = 0;
            String novodocUm = doc.substring(0, 4);
            String novodocDois = doc.substring(4, 12) + res;

            for (int i = 0; i < novodocUm.length(); i++) {
                novodigp1 += Integer.parseInt(String.valueOf(novodocUm.charAt(i))) * novaparteUm;
                //System.out.println(Integer.parseInt(String.valueOf(novodocUm.charAt(i))) +"+"+ novaparteUm+ "=" +novodigp1);
                novaparteUm--;
            }

            for (int i = 0; i < novodocDois.length(); i++) {
                novodigp2 += Integer.parseInt(String.valueOf(novodocDois.charAt(i))) * novaparteDois;
                //System.out.println(Integer.parseInt(String.valueOf(novodocDois.charAt(i))) +"+"+ novaparteDois+ "=" +novodigp2);
                novaparteDois--;
            }

            int div2 = (novodigp1 + novodigp2) / 11;
            int res2 = (novodigp1 + novodigp2) - (11 *div2);

            if(res2 < 2) fil = "0";
            if(res2 <= 10)
        }
 */
//#endregion
//#region cpf tips
/*
        public static String CPF(String doc) {
            String nine;
            if (doc.length() == 11) {
                nine = doc.substring(0, 9);

                String dig1 = digitoCPF(nine, 10);
                String dig2 = digitoCPF(nine + dig1, 11);

                return (nine + dig1 + dig2).equals(doc) ? doc : "documento inválido - " + (nine + "-" + dig1 + dig2);
            } else return "quantidade de dígitos inválidos";
        }

        public static String digitoCPF(String doc, int num){

            int dig = 0;
            for (int i = 0; i < doc.length(); i++) {
                dig += Integer.parseInt(String.valueOf(doc.charAt(i))) * num;
                num--;
            }

            int sub = dig - (11 * (dig/11));
            if(sub < 2) return "0";
            if(sub <= 10) return String.valueOf(11 - sub);
            return "";
        }



public static String CPF(String doc) {
    String nine = null;
    if (doc.length() == 11)  nine = doc.substring(0, 9);

    assert nine != null;
    String dig1 = returnDigito(doc(0, nine, 0, 10));
    String dig2 = returnDigito(doc(0, nine + dig1, 0, 11));

    return (nine + dig1 + dig2).equals(doc) ? doc : "documento inválido - " + (nine + "-" + dig1 + dig2);
}

public static int doc(int soma, String id, int max, int min) {

    int dg;
    if (min != 1) {
        dg = Integer.parseInt(String.valueOf(id.charAt(max)));
        soma += dg * min;
    } else return soma;

    return doc(soma, id, max + 1, min - 1);
}

public static String returnDigito(int dig){

    int sub = dig - (11 * (dig/11));
    if(sub < 2) return "0";
    if(sub <= 10) return String.valueOf(11 - sub);
    return "";
}
*/
//#endregion
//#region Old StringBuffer getResponse
        /*
        private static StringBuffer getResponse(String NumCEP) throws Exception {

            impressaoLog.logGenerico<CEP> printLog = new impressaoLog.logGenerico<>();
            @SuppressWarnings("unchecked") Logger log = printLog.getLogRetorno((Class<CEP>) (Object) (CEP.class));

            String url = "https://viacep.com.br/ws/" + NumCEP + "/xml/";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));//"ISO-8859-1"));
            StringBuffer response = new StringBuffer();
            try {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine.replace('\u00A0', ' '));
                }
                in.close();
            } catch (Exception e) {
                //throw new RuntimeException(e.getMessage());
                log.warning("[" + CEP.class.getSimpleName() + "] " + e.getMessage());
            }
            return response;
        }
        */
//#endregion
//#region rascunho


        /*
                private enum Operation {
                    ENCRYPT, DECRYPT;
                }
        public enum Current {
            morning("Bom dia"), afternoon("Boa tarde"), evening("Boa noite");

            private final String day;

            Current(String value) {
                this.day = value;
            }

            public String getDay() {
                return day;
            }
        }

 //        public static void main(String[] args) {
//            System.out.println(CPF("41707485810"));
//            System.out.println(CNPJ("58577114000189"));
//        }

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


                ---------------

        String senha = "123456789";
        MessageDigest algorithm = MessageDigest.getInstance("MD5");
        byte messageDigest[] = algorithm.digest(senha.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(messageDigest);

        String tx ="";

        for(byte b : messageDigest) tx += b;
        System.out.println(tx);
        System.out.println(encoded);

        String newcam = null;
        for (int i = 0; i < encoded.length(); i++) newcam += (char) (encoded.charAt(i) - (encoded.length() * encoded.length()));

        MessageDigest algorithm_v2 = MessageDigest.getInstance("SHA-256");
        byte messageDigest_V2[] = algorithm.digest(newcam.getBytes(StandardCharsets.UTF_8));
        String encoded_V2 = Base64.getEncoder().encodeToString(messageDigest_V2);

        System.out.print("\n" + encoded_V2);
        //---------

        Base64.Encoder enc = Base64.getEncoder();
        Base64.Decoder dec = Base64.getDecoder();
        String str = "JorgeDaCapadocia";

        // encode data using BASE64
        String encoded = enc.encodeToString(str.getBytes(StandardCharsets.UTF_8));
        System.out.println("encoded value is \t" + encoded);

        // Decode data
        String decoded = new String(dec.decode(encoded));
        System.out.println("decoded value is \t" + decoded);
        System.out.println("original value is \t" + str);

Encryption and Decryption in AES (analisar a viabilidade)
https://howtodoinjava.com/java/java-security/java-aes-encryption-example/

java 6 - 10

import java.io.UnsupportedEncodingException;
import javax.xml.bind.DatatypeConverter;

public class EncodeString64 {
    public static void main(String[] args) throws UnsupportedEncodingException {

        String str = "77+9x6s=";
        // encode data using BASE64
        String encoded = DatatypeConverter.printBase64Binary(str.getBytes());
        System.out.println("encoded value is \t" + encoded);

        // Decode data
        String decoded = new String(DatatypeConverter.parseBase64Binary(encoded));
        System.out.println("decoded value is \t" + decoded);

        System.out.println("original value is \t" + str);
    }
}

*/
//#endregion
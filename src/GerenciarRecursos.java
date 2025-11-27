import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GerenciarRecursos {
	
	public static void Salvar(Serializable dados, String nomeArquivo) throws Exception {
		
		try(ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(nomeArquivo)))){
		oos.writeObject(dados);
		}
		
	}
	
	public static Object Consultar(String nomeArquivo) throws Exception{
		
		try(ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(nomeArquivo)))){
		return ois.readObject();
		}
		
	}
	
}

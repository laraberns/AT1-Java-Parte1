package infnet.infra;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import infnet.domain.Funcionario;
import infnet.domain.Horista;
import infnet.domain.Mensalista;
import infnet.domain.Vendedor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FuncionarioRepository {

    public List<Funcionario> findAll() {
        ArrayList<Funcionario> retorno = new ArrayList<>();
        try {
            //Obter a conexão com o banco de dados
            Firestore firestore = Firebase.getFirestore();
            //Consulta na coleção de funcionarios
            ApiFuture<QuerySnapshot> query = firestore.collection("funcionarios").get();
            QuerySnapshot querySnapshot = query.get();
            //Listar os documentos dessa coleção
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for(QueryDocumentSnapshot document : documents) {
                Funcionario funcionario = null;
                String tipo = document.getString("tipo");
                switch (tipo) {
                    case "mensalista" -> funcionario = new Mensalista(
                            document.getLong("matricula"),
                            document.getString("nome"),
                            document.getString("cargo"),
                            document.getDouble("salario")
                    );
                    case "vendedor" -> funcionario = new Vendedor(
                            document.getLong("matricula"),
                            document.getString("nome"),
                            document.getDouble("salario"),
                            document.getDouble("comissao"),
                            document.getDouble("totalVendas")
                    );
                    case "horista" -> funcionario = new Horista(
                            document.getLong("matricula"),
                            document.getString("nome"),
                            document.getString("cargo"),
                            document.getDouble("valorHora"),
                            document.getLong("totalHoras")
                    );
                }
                retorno.add(funcionario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

    public void saveMensalista(Mensalista mensalista) {
        try {
            Firestore firestore = Firebase.getFirestore();
            Map<String, Object> dados = new HashMap<>();
            dados.put("matricula", mensalista.getMatricula());
            dados.put("nome", mensalista.getNome());
            dados.put("cargo", mensalista.getCargo());
            dados.put("salario", mensalista.getSalario());
            dados.put("tipo", "mensalista");

            firestore.collection("funcionarios")
                    .document(String.valueOf(mensalista.getMatricula()))
                    .set(dados)
                    .get();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean matriculaExists(long matricula) {
        try {
            Firestore firestore = Firebase.getFirestore();
            var docRef = firestore.collection("funcionarios").document(String.valueOf(matricula));
            var docSnap = docRef.get().get();
            return docSnap.exists();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Mensalista> findAllMensalistas() {
        List<Mensalista> mensalistas = new ArrayList<>();
        try {
            Firestore firestore = Firebase.getFirestore();
            ApiFuture<QuerySnapshot> query = firestore.collection("funcionarios")
                    .whereEqualTo("tipo", "mensalista")
                    .get();
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

            for (QueryDocumentSnapshot document : documents) {
                Mensalista m = new Mensalista(
                        document.getLong("matricula"),
                        document.getString("nome"),
                        document.getString("cargo"),
                        document.getDouble("salario")
                );
                mensalistas.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mensalistas;
    }

    public Mensalista findMensalistaById(long matricula) {
        try {
            Firestore firestore = Firebase.getFirestore();
            var docRef = firestore.collection("funcionarios").document(String.valueOf(matricula));
            var docSnap = docRef.get().get();

            if (docSnap.exists() && "mensalista".equals(docSnap.getString("tipo"))) {
                return new Mensalista(
                        docSnap.getLong("matricula"),
                        docSnap.getString("nome"),
                        docSnap.getString("cargo"),
                        docSnap.getDouble("salario")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}

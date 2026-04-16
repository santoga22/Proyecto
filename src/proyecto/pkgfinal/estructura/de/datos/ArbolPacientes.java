/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkgfinal.estructura.de.datos;

/**
 *
 * @author usuario
 */
public class ArbolPacientes {
    private NodoArbol raiz;
    
    public void insertar(Paciente p){
        raiz=insertar(p,raiz);
    }
    
    private NodoArbol insertar(Paciente p, NodoArbol r){
        if(r==null)return new NodoArbol(p);//es vacio
        if(p.getId().compareTo(r.getDato().getId())<0){
            r.setIzq(insertar(p,r.getIzq()));
        }else{
            r.setDer(insertar(p,r.getDer()));
        }
        return r;
    }    
    
    public Paciente buscar(String id){
        return buscar(id, raiz);
    }
    
    private Paciente buscar (String id, NodoArbol r){ //buscar privado en el arbol por id de paciente
        if (r == null) return null;
        if(id.equals(r.getDato().getId())){
            return r.getDato();
        }
        if(id.compareTo(r.getDato().getId())<0){
            return buscar(id, r.getIzq());
        }else{
            return buscar(id, r.getDer());
        }
    }
    public String inOrden() {
        StringBuilder r= new StringBuilder();
        inOrden(raiz, r);
        return r.toString();
    }
    private void inOrden(NodoArbol r, StringBuilder sb){
        if(r!=null){
            inOrden(r.getIzq(),sb);
            sb.append(r.getDato()).append("\n");
            inOrden(r.getDer(),sb);
        }
    }
    
    public String toArchivo(){
        StringBuilder r= new StringBuilder();
        toArchivo(raiz, r);
        return r.toString();
    }
    
        private void toArchivo(NodoArbol r, StringBuilder sb){
        if(r!=null){
            toArchivo(r.getIzq(),sb);
            sb.append(r.getDato()).append("\n");
            toArchivo(r.getDer(),sb);
        }
    }
}

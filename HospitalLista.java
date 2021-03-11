package projvirtual2_hospital;

import java.util.ArrayList;
import java.util.Iterator;

public class HospitalLista {
    private ArrayList<Hospital>  listaHospitais;
    
    public HospitalLista(){
         listaHospitais = new ArrayList<Hospital>();        
    }
    public void insereHospital(Hospital h){
        listaHospitais.add(h);
    }  
    public int totalLeitosVazios(){
        Iterator<Hospital> it = listaHospitais.iterator();
        int total = 0;        
        while(it.hasNext()){
            // total = it.next().leitosDisponiveis();--> Alternativa            
            Hospital h = it.next();
            total += h.leitosDisponiveis();
        }
        return total;
    }
    public void limpaLista(){
        listaHospitais.clear();
    }
    public ArrayList<Hospital> getListaHospitais() {
        return listaHospitais;
    }
    public void removeHospital(String nome){
        Iterator<Hospital> it = listaHospitais.iterator();          
        boolean achei = false;
        while(it.hasNext() && !achei ){
           Hospital h = it.next();
           if(h.getNomeHospital().equalsIgnoreCase(nome)){
               listaHospitais.remove(h);
               achei = true;               
           }           
        }
    }
}
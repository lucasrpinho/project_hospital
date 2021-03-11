/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projvirtual2_hospital;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GerenteHospitais {
       private HospitaisJFrame hospitalView;
       private HospitalLista   hospitalLista;
       private ControladorHospitalSGBD hospitalDB;
       
       private class alteraListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {           
            Hospital h = hospitalView.obterHospital();            
           // hospitalDB.removeHospital(h);
           // hospitalDB.insereHospital(h);
            hospitalDB.alteraHospital(h);
            hospitalDB.lerListaHospital(hospitalLista);
            hospitalView.mostraLista(hospitalLista.getListaHospitais());
        }           
       }
    
       private class insereListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
             Hospital h = hospitalView.obterHospital();
             hospitalLista.insereHospital(h);
             hospitalView.insereHospitalLista(h); 
             hospitalDB.insereHospital(h);
        }           
       }
       private class totalListener implements ActionListener{
         @Override
         public void actionPerformed(ActionEvent e) {
             int total = hospitalLista.totalLeitosVazios();
             hospitalView.insereTotalLeitos(total);           
         }
       }
       private class limpaLista implements ActionListener{

          @Override
          public void actionPerformed(ActionEvent e) {
            hospitalView.limpaListaVisual();
            hospitalLista.limpaLista();
            hospitalDB.removeHospital();
          }           
       }
       private class removeHospitalManip implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Hospital h = hospitalView.obterHospital();
            hospitalLista.removeHospital(h.getNomeHospital());            
            hospitalView.mostraLista(hospitalLista.getListaHospitais());
            hospitalDB.removeHospital(h);
            hospitalDB.lerListaHospital(hospitalLista);
        }           
       }      
       
    public void executar(){
        hospitalView  = new HospitaisJFrame();
        hospitalLista = new HospitalLista();
        //////
        insereListener x = new insereListener();
        hospitalView.addInsereListener(x);
        
        //////////////
        hospitalView.colocaManipuladorDeTotal(new totalListener());
        hospitalView.colocaLimpaManip(new limpaLista());
        hospitalView.colocaRemoveManip(new removeHospitalManip());
        hospitalView.colocaAlteraManip(new alteraListener());
         ////////////
        hospitalView.setVisible(true);   
        //Cria o controlador do banco e conecta
        hospitalDB = new ControladorHospitalSGBD();
       
        //Le a lista corrente do banco e coloca na lista local
        hospitalDB.lerListaHospital(hospitalLista);
        
        //Coloca a lista do banco na lista visual
        hospitalView.mostraLista(hospitalLista.getListaHospitais());
    }
    
    
}

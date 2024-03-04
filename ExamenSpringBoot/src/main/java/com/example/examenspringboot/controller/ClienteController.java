package com.example.examenspringboot.controller;

import com.example.examenspringboot.clase.Cliente;
import com.example.examenspringboot.repositorio.RepositorioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ClienteController {
    @Autowired
    private RepositorioCliente repoCliente;

    @PostMapping("save")
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
        Cliente clienteNuevo=cliente;
        if(clienteNuevo!=null){
            return new ResponseEntity<>(repoCliente.save(cliente), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("getClienteById/{id}")
    public ResponseEntity<Cliente>getClienteById(@PathVariable Long id){
        if(repoCliente.existsById(id)){
            return new ResponseEntity<>(repoCliente.findById(id).get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("getClienteByCantidadMayor/{cantidad}")
    public ResponseEntity<Object> getClienteByCantidadMayor(@PathVariable Long cantidad){
        List<Cliente> clientes=repoCliente.getCantidadMayor(cantidad);
        if(!clientes.isEmpty()){
            return new ResponseEntity<Object>(clientes,HttpStatus.OK);
        }else{
            return new ResponseEntity<Object>("No hay Clientes con ventas superiores a "+cantidad,HttpStatus.NOT_FOUND);
        }

    }
@GetMapping("totalVentas")
    public ResponseEntity<Long>totalVentas(){
        List<Cliente> clientes=repoCliente.findAll();
        Long sumatorioVentas=0L;
        if(!clientes.isEmpty()){
            for(Cliente cliente:clientes){
                sumatorioVentas+=cliente.getTotal();
            }
            return new ResponseEntity<>(sumatorioVentas,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(0L,HttpStatus.NOT_FOUND);
        }

}
    @GetMapping("promedio")
    public ResponseEntity<Double>promedio(){
        List<Cliente>clienteActivos=repoCliente.getClienteByEstadoEquals("activo");
        Long sumatorioVentas=0L;
        if(!clienteActivos.isEmpty()){
            for(Cliente cliente:clienteActivos){
                sumatorioVentas+=cliente.getTotal();
            }
            Double promedio=(double)sumatorioVentas/clienteActivos.size();
            return new ResponseEntity<>(promedio,HttpStatus.OK);
        }else{
            return new ResponseEntity<>((double)clienteActivos.size(),HttpStatus.NOT_FOUND);
        }


    }
@GetMapping("getClienteInactivoByVentas")
    public ResponseEntity<Long> getCantidad(){
        List<Cliente> clientesInactivos=repoCliente.getVentasInactivo();

        if(!clientesInactivos.isEmpty()){
            return new ResponseEntity<>((long)clientesInactivos.size(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
}

}

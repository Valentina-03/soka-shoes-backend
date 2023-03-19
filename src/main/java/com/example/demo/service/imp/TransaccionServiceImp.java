package com.example.demo.service.imp;

import com.example.demo.dao.TransaccionDAO;
import com.example.demo.model.Transaccionp;
import com.example.demo.negocio.SokaShoes;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.service.TransaccionService;

@Service
public class TransaccionServiceImp implements TransaccionService{

    @Autowired
    public TransaccionDAO tdao;
    
    
    @Override
    @Transactional
    public void guardar(Transaccionp payu) {
        tdao.save(payu);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaccionp> listar() {
        return tdao.findAll();
    }

    @Override
    @Transactional
    public void eliminar(String id) {
        tdao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Transaccionp encontrar(String id) {
        return tdao.findById(id).get();
    }

	@Override
	@Transactional(readOnly = true)
	public Transaccionp getDatos(Map<String, String> body) 
	{
		SokaShoes nexp = new SokaShoes();
		Transaccionp pay = new Transaccionp();
		
		pay.setAttempts(Short.parseShort(body.get("attempts")));
        pay.setBankId(Short.parseShort(body.get("bank_id")));
        pay.setBillingCountry((body.get("billing_country")) + "");
        pay.setCurrency(body.get("currency") + "");
        pay.setDate(nexp.convertirFecha(body.get("date"), "\\."));
        pay.setDescription(body.get("description") + "");
        pay.setEmailBuyer(body.get("email_buyer") + "");
        pay.setOperationDate((nexp.convertirFecha(body.get("operation_date"), "\\-")));
        pay.setPaymentMethod(Short.parseShort(body.get("payment_method")));
        pay.setPaymentMethodName(body.get("payment_method_name") + "");
        pay.setPaymentMethodType(Short.parseShort(body.get("payment_method")));
        pay.setPseBank(body.get("pse_bank") + "");
        pay.setResponseMessagePol(body.get("response_message_pol"));
        pay.setShippingCountry(body.get("shipping_country"));
        pay.setTax(body.get("tax"));
        pay.setTransactionDate(nexp.convertirFecha(body.get("transaction_date"), "\\-"));
        pay.setTransactionId(body.get("transaction_id"));
        pay.setValue(Long.parseLong(body.get("value").split("\\.")[0]));
		
        return pay;
	}   
}
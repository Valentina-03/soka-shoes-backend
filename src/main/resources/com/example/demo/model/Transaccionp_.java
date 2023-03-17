package com.example.demo.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-03-16T19:58:19.891-0500")
@StaticMetamodel(Transaccionp.class)
public class Transaccionp_ {
	public static volatile SingularAttribute<Transaccionp, String> transactionId;
	public static volatile SingularAttribute<Transaccionp, Date> date;
	public static volatile SingularAttribute<Transaccionp, Short> paymentMethodType;
	public static volatile SingularAttribute<Transaccionp, Date> operationDate;
	public static volatile SingularAttribute<Transaccionp, Short> bankId;
	public static volatile SingularAttribute<Transaccionp, Short> paymentMethod;
	public static volatile SingularAttribute<Transaccionp, Short> attempts;
	public static volatile SingularAttribute<Transaccionp, Date> transactionDate;
	public static volatile SingularAttribute<Transaccionp, String> tax;
	public static volatile SingularAttribute<Transaccionp, String> pseBank;
	public static volatile SingularAttribute<Transaccionp, String> shippingCountry;
	public static volatile SingularAttribute<Transaccionp, String> description;
	public static volatile SingularAttribute<Transaccionp, String> currency;
	public static volatile SingularAttribute<Transaccionp, Long> value;
	public static volatile SingularAttribute<Transaccionp, String> billingCountry;
	public static volatile SingularAttribute<Transaccionp, String> paymentMethodName;
	public static volatile SingularAttribute<Transaccionp, String> emailBuyer;
	public static volatile SingularAttribute<Transaccionp, Short> paymentMethodId;
	public static volatile SingularAttribute<Transaccionp, String> responseMessagePol;
	public static volatile SingularAttribute<Transaccionp, Compra> referenceSale;
}

package pl.jaceksysiak.spring.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("pl/jaceksysiak/spring/test/beans/beans.xml");
 
		OffersDAO offersDao  = (OffersDAO)context.getBean("offersDao");
		
		//batch insert
		List<Offer> bchcr_offers = new ArrayList<Offer>();
		bchcr_offers.add(new Offer(1, "alex1", "dd1@wp.pl", "alex1alex1alex1alex1alex1alex1"));
		bchcr_offers.add(new Offer(2,"alex2", "dd2@wp.pl", "alex2alex2alex2alex2alex2alex2"));
		bchcr_offers.add(new Offer(3, "alex3", "dd3@wp.pl", "alex3alex3alex3alex3alex3alex3alex3"));
		bchcr_offers.add(new Offer(4, "alex4", "dd4@wp.pl", "alex4alex4alex4alex4alex4alex4alex4alex4alex4"));
		
		int[] rvals = offersDao.create(bchcr_offers);
		
		for(int value : rvals){
			System.out.println("Create object in batch process: " + value + " rows.");
		}
		
		
		//create offer
		try{
			//insert offer
			Offer offer1 = new Offer("ula", "p9@wp.pl", "uuuuuuuuuuuuuuuuu");
			Offer offer2 = new Offer("ola", "p10@wp.pl", "oooooooooooooooooooo");
			Offer offer3 = new Offer("kola", "p11@wp.pl", "kkkkkkkkkk");
			
			if(offersDao.create(offer1)){
				System.out.println("offer1 created.");
			}
			
			if(offersDao.create(offer2)){
				System.out.println("offer2 created.");
			}
			
			if(offersDao.create(offer3)){
				System.out.println("offer3 created.");
			}
			
			
			//delete record
			//offersDao.delete(4);
			
			//updaterecord
			Offer upd_offer= new Offer(1, "to updated name ......", "to updated email ......", "to updated text ......");
			if(offersDao.update(upd_offer)){
			  System.out.println("Object id=1 updated.");	
			} else {
				System.out.println("Cannot update object.");
			}
			
			
		List<Offer> offers = offersDao.getOffers();
		
		for(Offer offer : offers){
			
			 System.out.println(offer);
		} 
		
		Offer offer = offersDao.getOffer(1);
		System.out.println("Should be record with id=1: "+ offer);
		
			} catch(CannotGetJdbcConnectionException ex){
				System.out.println("Cannot get database connection."); 
			}
			  catch(DataAccessException ex){
				System.out.println(ex.getMessage());
				System.out.println(ex.getClass());
			}
	 
		((ClassPathXmlApplicationContext)context).close();

	}

}





























































package ServiceBank.DAO;

import ServiceBank.Model.ClientAccount;
import lombok.SneakyThrows;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.List;

@Component
public class SearchClientDaoHibernet {
//
//
//
//private final SessionFactory sessionFactory;
//
//    public SearchClientDaoHibernet(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//    @SneakyThrows
//    @Transactional
//    public  void  save(ClientAccount clientAccount) {
//        Session session = null;
//        session = sessionFactory.getCurrentSession();
//        session.save(clientAccount);
//    }
//    @SneakyThrows
//    @Transactional(readOnly = true)
//    //вывести всех клиентов у которых дата рождения больше чем в запросе
//      public List<ClientAccount> clientAccountListData(Data queryData) {
//        Session session = null;
//        session = sessionFactory.getCurrentSession();
//        List<ClientAccount> clientAccountListData= session.createQuery("From ClitntAccount WHERE data>queryData",ClientAccount.class).getResultList();
//    return clientAccountListData;
//}
//
////вывести клиента с телефон из запроса
//    @SneakyThrows
//    @Transactional(readOnly = true)
//public ClientAccount clientAccountListPhone(String queryNumbers){
//    Session session = sessionFactory.getCurrentSession();
//ClientAccount clientAccount =  session.createQuery("From ClitntAccount WHERE numbers.queryNumbers",ClientAccount.class).getSingleResult();
//    return clientAccount;
//}
//@SneakyThrows
//@Transactional(readOnly = true)
////вывести клиента с email из запроса
//public ClientAccount clientAccountListEmail(String queryEmail){
//    Session session = sessionFactory.getCurrentSession();
//    ClientAccount clientAccountE =  session.createQuery("From ClitntAccount WHERE emails.queryEmail",ClientAccount.class).getSingleResult();
//    return clientAccountE;
//}
//// если передана фамилия то по Like
//    @SneakyThrows
//    @Transactional(readOnly = true)
//public ClientAccount clientAccountListName(String queryName){
//    Session session = sessionFactory.getCurrentSession();
//    ClientAccount clientAccountName =  session.createQuery("From ClitntAccount WHERE name.queryName LIKE '%text-from-request-param%",ClientAccount.class).getSingleResult();
//    return clientAccountName;
//}
}

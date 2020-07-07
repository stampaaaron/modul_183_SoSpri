package ch.bbw.pr.sospri.repository;

import ch.bbw.pr.sospri.domain.User;
import org.springframework.data.repository.CrudRepository;
/**
 * MemberRepository
 * 
 * @author Peter Rutschmann
 * @version 26.03.2020
 */
                                                       //Klasse, id-Typ
public interface UserRepository extends CrudRepository<User, Long>{
	//Da wir eine embedded database verwenden, braucht es keine Conecction Information.

  User findByUsername(String username);

}


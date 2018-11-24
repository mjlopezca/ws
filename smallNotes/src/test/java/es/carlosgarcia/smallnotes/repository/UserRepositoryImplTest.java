package es.carlosgarcia.smallnotes.repository;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import es.carlosgarcia.smallnotes.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:smallNotes-test.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserRepositoryImplTest {
	private static final String EMAIL_TEST1 = "user1@smallnotes.es";
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void shouldReadUserByEmail(){
		User user = userRepository.getUserByEmail(EMAIL_TEST1);
		Assert.assertNotNull(user);
		Assert.assertEquals(EMAIL_TEST1, user.getEmail());
	}
}

package es.carlosgarcia.smallnotes.repository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.carlosgarcia.smallnotes.model.Note;

/**
 * Repository for Notes Impl
 * @author Carlos García
 * @see http://carlos-garcia.es
 */
@Repository
public class NoteRepositoryImpl extends GenericRepositoryImpl<Note> implements NoteRepository {
	private final Logger logger = LoggerFactory.getLogger(NoteRepositoryImpl.class);
	
	private static final long serialVersionUID = -4599832253548800012L;

	@Override
	public List<Note> getAllByUserEmail(String userEmail, Map<String,Object> filterParams){
		logger.debug("getting notes by user email {}", userEmail);
		
		Map<String,Object> queryParams = new HashMap<String, Object>();
		if (filterParams != null){
			queryParams.putAll(filterParams);
		}
		
		
		StringBuilder query = new StringBuilder(128);
		query.append("select n from Note n where n.owner.email = :email");
		

		Iterator<String> fieldNames = queryParams.keySet().iterator();
		while (fieldNames.hasNext()){
			String fieldName = fieldNames.next();
			query.append(" and n." + fieldName + " = :" + fieldName);
		}

		queryParams.put("email", userEmail);
	
		List<Note> notes = this.getAllByQuery(query.toString(), queryParams);
		
		if (logger.isDebugEnabled()){
			int numNotes = 0;
			if (notes != null){
				numNotes = CollectionUtils.size(notes);
			} 
			logger.debug("email {} num. notes readed {}", userEmail, numNotes);			
		}
		
		return notes;
	}
}

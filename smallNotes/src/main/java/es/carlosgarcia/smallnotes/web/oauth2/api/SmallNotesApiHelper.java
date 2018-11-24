package es.carlosgarcia.smallnotes.web.oauth2.api;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import es.carlosgarcia.smallnotes.model.Note;

/**
 * Helper method for SmallNotesApi
 * @author Carlos García
 * @see http://carlos-garcia.es
 */
public class SmallNotesApiHelper {
	
	public static ResponseEntity<RestApiError> createAndSendResponse(RestApiError restApiError) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Cache-Control", "no-store");
		headers.set("Pragma", "no-cache");
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<RestApiError> responseEntity = new ResponseEntity<RestApiError>(restApiError, headers, restApiError.getHttpStatusCode());
		return responseEntity;
	}
	
	/**
	 * Parse filter string to build a map with format <paramFieldKey,paramFieldValue>.
	 * @param filter Query String, Example: title:titulo1,content:Contenido1
	 * @return A map with format <paramFieldKey,paramFieldValue>
	 */
	public static Map<String, Object> parseFilter(String filter) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		StringTokenizer fields  = new StringTokenizer(filter, ",");
		Note note = new Note();
		String fieldName  = null;
		
		try {
			while (fields.hasMoreTokens()){
				StringTokenizer field = new StringTokenizer(fields.nextToken(), ":");
				if (field.countTokens() != 2){
					throw new IllegalArgumentException(filter);
				}
				
				fieldName  = field.nextToken();
				String fieldValue = field.nextToken();
				
				// Verify that fieldName exists as field on Note class
				PropertyUtils.getSimpleProperty(note, fieldName);
	
				map.put(fieldName, fieldValue);
			}
		} catch (NoSuchMethodException e) {
			throw new IllegalArgumentException(String.format("Property %s does not exists", fieldName));
		} catch (IllegalAccessException ex){
			throw new IllegalArgumentException(filter);
		} catch (InvocationTargetException ex){
			throw new IllegalArgumentException(filter);
		}
		
		return map;
	}
	
	/**
	 * Apply partial response to Note
	 * @param notes Full property note 
	 * @param fields Fields to return.
	 * @return A Note with only "fields" with values. 
	 */	
	public static Note applyPartialResponse(Note note, String fields){
		String[] fieldsToIgnore =  SmallNotesApiHelper.constructPartialResponseFieldsToIgnore(fields);
		Note partialResponseNote = new Note();
		BeanUtils.copyProperties(note, partialResponseNote, fieldsToIgnore);
		return partialResponseNote;			
	}
	
	
	/**
	 * Apply partial response.
	 * @param notes Full property note list 
	 * @param fields Fields to return.
	 * @return A Note list with only "fields" with values. 
	 */
	public static List<Note> applyPartialResponse(List<Note> notes, String fields){
		String[] fieldsToIgnore =  SmallNotesApiHelper.constructPartialResponseFieldsToIgnore(fields);
		
		List<Note> partialResponseNotes = new ArrayList<Note>(notes.size());
		for (Note note : notes){
			Note partialResponseNote = new Note();
			BeanUtils.copyProperties(note, partialResponseNote, fieldsToIgnore);
			partialResponseNotes.add(partialResponseNote);
		}
		
		// Important: Note class is has @JsonSerialize(include = Inclusion.NON_DEFAULT) setting and for this reason Partial response is working fine. 
		return partialResponseNotes;
	}
	
	
	/**
	 * @param fields Fields that user want to get response only.
	 * @return And String[] with the others fields, (fields to be ignored)
	 */
	private static String[] constructPartialResponseFieldsToIgnore(String fields){
		 String[] partialResponseFields = fields.split(",");
		 String	  currentFieldName = null;
		 
		 Note note = new Note();
		 
		 // First, validate that all fields exists on class
		 try {
			 for (int i = 0, num = partialResponseFields.length; i < num; i++){
				 currentFieldName = partialResponseFields[i];
				 PropertyUtils.getSimpleProperty(note, currentFieldName);
			 }
		 } catch (NoSuchMethodException e) {
			 throw new IllegalArgumentException(String.format("Property %s does not exists", currentFieldName));
		 } catch (IllegalAccessException ex){
			 throw new IllegalArgumentException(fields);
		 } catch (InvocationTargetException ex){
			 throw new IllegalArgumentException(fields);
		 } catch (Exception ex){
			 throw new IllegalArgumentException(fields);
		 }
			
		 
		 Set<String> fieldsToIgnoreList = new TreeSet<String>();
		 
		// Constuct Field to Ignore array
		PropertyDescriptor[] properties = BeanUtils.getPropertyDescriptors(note.getClass());
		for (int i = 0, numProperties = properties.length; i < numProperties; i++){
			if (! ArrayUtils.contains(partialResponseFields, properties[i].getName())){
				fieldsToIgnoreList.add(properties[i].getName());
			}
		}
		String[] fieldsToIgnore =  fieldsToIgnoreList.toArray(new String[0]);
		
		return fieldsToIgnore;
	}
}

/**
 * 
 */
package com.google.code.stackoverflow.schema.adapter.json;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.code.stackoverflow.schema.Question;
import com.google.code.stackoverflow.schema.Questions;
import com.google.code.stackoverflow.schema.adapter.Adaptable;

/**
 * @author nmukhtar
 *
 */
public class QuestionsImpl extends BaseJsonAdapter implements Questions, Adaptable<Questions, JSONObject> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5190225278764284533L;
	
	private List<Question> questions = new ArrayList<Question>();

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public void adaptFrom(JSONObject adaptee) {
		copyProperties(this, adaptee);
		JSONArray questions = (JSONArray) adaptee.get("questions");
		if (questions != null) {
			for (Object o : questions) {			
				QuestionImpl question = new QuestionImpl();
				question.adaptFrom((JSONObject) o);
				getQuestions().add(question);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject adaptTo() {
		JSONObject adapter = new JSONObject();
		copyProperties(adapter, this);
		JSONArray questions = new JSONArray();
		for (Question question : getQuestions()) {
			questions.add(((QuestionImpl) question).adaptTo());
		}
		adapter.put("questions", questions);
		return adapter;
	}
}

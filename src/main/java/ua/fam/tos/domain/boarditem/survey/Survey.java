package ua.fam.tos.domain.boarditem.survey;
import ua.fam.tos.domain.boarditem.BoardItem;
import ua.fam.tos.domain.Contributor;
import ua.fam.tos.domain.boarditem.Submittable;
import ua.fam.tos.domain.exceptions.SubmissionException;

import java.util.*;


public class Survey extends BoardItem implements Submittable {

    private final List<SurveyQuestion> questions;
    private final Map<String, SurveyStatus> submissionStatuses;

    public Survey() {
        this.questions = new ArrayList<>();
        this.submissionStatuses = new HashMap<>();
    }

    @Override
    public void submit(Contributor contributor) {
        if (submissionStatuses.get(contributor.getUsername()) == SurveyStatus.SUBMITTED) {
            throw new SubmissionException("You can't cancel submission");
        }

        for (SurveyQuestion question : questions) {
            if (question.getUserAnswers().get(contributor.getUsername()) == null ||
                    question.getUserAnswers().get(contributor.getUsername()).isEmpty()) {
                throw new SubmissionException("All questions must be answered before submission.");
            }
        }

        submissionStatuses.put(contributor.getUsername(), SurveyStatus.SUBMITTED);
    }


    public void addQuestion(String questionText){
        questions.add(new SurveyQuestion(questionText));
    }

    public void deleteQuestionByIndex(int index){
        if (index < 0 || index > questions.size()){
            throw new SurveyModificationException("Index out of bounds.");
        }
        questions.remove(index);
    }

    public List<SurveyQuestion> getQuestions(){
        return questions;
    }

    public Optional<SurveyQuestion> getQuestionByIndex(int index){
        if (index < 0 || index > questions.size()){
            return Optional.empty();
        }
        return Optional.of(questions.get(index));
    }

    @Override
    public void cancel(Contributor contributor) {
        throw new SubmissionException("You can't cancel submission");
    }

    public Map<String, SurveyStatus> getSubmissionStatuses() {
        return submissionStatuses;
    }

    @Override
    public boolean isKnowAboutContributorWithUsername(String username){
        return ((submissionStatuses.containsKey(username))||
                (getCreator().getUsername().equals(username)));
    }
}

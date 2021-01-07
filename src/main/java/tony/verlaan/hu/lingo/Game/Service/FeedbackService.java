package tony.verlaan.hu.lingo.Game.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tony.verlaan.hu.lingo.Game.Domain.Feedback;
import tony.verlaan.hu.lingo.Game.Repository.FeedbackRepository;

import java.util.Set;

@Service
public class FeedbackService {

    private final FeedbackRepository<Feedback> feedbackRepository;

    public FeedbackService(FeedbackRepository<Feedback> feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public void saveFeedback(Set<Feedback> feedbackSet) {
        for (Feedback feedback : feedbackSet) {
            feedbackRepository.save(feedback);
        }
    }
}

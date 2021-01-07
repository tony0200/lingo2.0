package tony.verlaan.hu.lingo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tony.verlaan.hu.lingo.DTO.Guess;
import tony.verlaan.hu.lingo.Game.Controller.GameController;
import tony.verlaan.hu.lingo.Game.Domain.Game;

@Controller
public class webGameController {

    private final GameController gameController;

    public webGameController(GameController gameController) {
        this.gameController = gameController;
    }

    @GetMapping("/")
    public ModelAndView home(ModelMap modelMap) {
        modelMap.addAttribute("scores", gameController.getTopTen());
        return new ModelAndView("pages/Highscore");
    }

    @GetMapping("/game")
    public ModelAndView game(ModelMap modelMap, @AuthenticationPrincipal OAuth2User principal) {
        Game game = gameController.avtiveGame((String) principal.getAttributes().get("email"));
        modelMap.addAttribute("game", game);
        return new ModelAndView("pages/Game");
    }

    @PostMapping("/game/guess")
    public ModelAndView move(ModelMap modelMap, @ModelAttribute Guess guess, @AuthenticationPrincipal OAuth2User principal) {
        Game game = gameController.nextMove(guess.getWord(), (String) principal.getAttributes().get("email"));
        if (game.getEndDate() != null) {
             return submitName();
        } else {
            return game(modelMap, principal);
        }
    }

    @PostMapping("/game/submit")
    public ModelAndView submit(ModelMap modelMap, @AuthenticationPrincipal OAuth2User principal, @RequestParam String name) {
            gameController.setScore(name, (String) principal.getAttributes().get("email"));
            return game(modelMap, principal);
    }

    private ModelAndView submitName() {
        return new ModelAndView("pages/Submit");
    }
}

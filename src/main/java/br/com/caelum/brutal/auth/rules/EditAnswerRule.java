package br.com.caelum.brutal.auth.rules;

import static br.com.caelum.brutal.auth.rules.ComposedRule.composedRule;
import static br.com.caelum.brutal.auth.rules.Rules.hasKarma;
import static br.com.caelum.brutal.auth.rules.Rules.isAuthor;
import static br.com.caelum.brutal.auth.rules.Rules.isModerator;
import br.com.caelum.brutal.brutauth.auth.rules.CustomBrutauthRule;
import br.com.caelum.brutal.model.Answer;
import br.com.caelum.brutal.model.User;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class EditAnswerRule implements CustomBrutauthRule{
	private final User user;
	
	public EditAnswerRule(User user) {
		this.user = user;
	}
	
	public boolean isAllowed(Answer answer) {
		int karma = PermissionRulesConstants.EDIT_ANSWER;
		return composedRule(isAuthor()).or(hasKarma(karma)).or(isModerator()).isAllowed(user, answer);
	}
}
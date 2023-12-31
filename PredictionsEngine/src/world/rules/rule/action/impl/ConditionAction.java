package world.rules.rule.action.impl;

import world.entities.EntitiesDefinition;
import world.entities.entity.EntityInstance;
import world.rules.rule.action.api.AbstractAction;
import world.rules.rule.action.api.Action;
import world.rules.rule.action.api.ActionType;
import world.rules.rule.action.condition.api.Condition;
import java.util.List;

public class ConditionAction extends AbstractAction {
    private final Condition condition;
    private final List<Action> thenActions;
    private final List<Action> elseActions;
    public ConditionAction (EntitiesDefinition entitiesDefinition, String entityName, List<Action> thenActions, List<Action> elseActions, Condition condition){
        super(ActionType.CONDITION, entitiesDefinition, entityName);
        this.elseActions = elseActions;
        this.thenActions = thenActions;
        this.condition = condition;
    }

    @Override
    public void invoke(EntityInstance entityInstance) {
        if (condition.evaluate(entityInstance)) {
            performActions(thenActions, entityInstance);
        } else if (elseActions != null){
            performActions(elseActions, entityInstance);
        }
    }

    @Override
    public String getPropertyName() {
        return null;
    }


    @Override
    public String getByExpression() {
        return null;
    }

    private void performActions(List<Action> actions, EntityInstance entityInstance){
        for (Action action : actions)
            action.invoke(entityInstance);
    }

    public List<Action> getThenActions() {
        return thenActions;
    }

    public List<Action> getElseActions() {
        return elseActions;
    }

    @Override
    public String toString() {
        return "ConditionAction{" +
                "condition=" + condition +
                ", entityName='" + entityName + '\'' +
                ", thenActions=" + thenActions +
                ", elseActions=" + elseActions +
                '}';
    }
}

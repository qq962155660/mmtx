/*
 *  Copyright 1999-2019 Mmtx.io Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.mmtx.saga.engine.evaluation.expression;

import io.mmtx.saga.engine.evaluation.Evaluator;
import io.mmtx.saga.engine.evaluation.EvaluatorFactory;
import io.mmtx.saga.engine.expression.ExpressionFactory;

/**
 * Expression evaluator factory
 *
 * @author lorne.cl
 */
public class ExpressionEvaluatorFactory implements EvaluatorFactory {

    private ExpressionFactory expressionFactory;

    @Override
    public Evaluator createEvaluator(String expressionString) {

        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        evaluator.setExpression(this.expressionFactory.createExpression(expressionString));
        return evaluator;
    }

    public ExpressionFactory getExpressionFactory() {
        return expressionFactory;
    }

    public void setExpressionFactory(ExpressionFactory expressionFactory) {
        this.expressionFactory = expressionFactory;
    }
}
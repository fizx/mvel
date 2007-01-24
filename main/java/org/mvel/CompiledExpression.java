/**
 * MVEL (The MVFLEX Expression Language)
 *
 * Copyright (C) 2007 Christopher Brock, MVFLEX/Valhalla Project and the Codehaus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.mvel;

import org.mvel.integration.VariableResolverFactory;

import java.io.Serializable;

public class CompiledExpression implements Serializable, ExecutableStatement {
    private char[] expression;
    private TokenIterator tokenMap;

    private Class knownEgressType;
    private Class knownIngressType;

    private boolean convertableIngressEgress;

    private transient ExpressionParser expressionParser;

    public CompiledExpression(char[] expression, TokenIterator tokenMap) {
        this.expression = expression;
        this.tokenMap = new FastTokenIterator(tokenMap);
    }

    public char[] getExpression() {
        return expression;
    }

    public void setExpression(char[] expression) {
        this.expression = expression;
    }

    public TokenIterator getTokenMap() {
        return tokenMap;
    }

    public void setTokenMap(TokenIterator tokenMap) {
        this.tokenMap = new FastTokenIterator(tokenMap);
    }


    public Class getKnownEgressType() {
        return knownEgressType;
    }

    public void setKnownEgressType(Class knownEgressType) {
        this.knownEgressType = knownEgressType;
    }


    public Class getKnownIngressType() {
        return knownIngressType;
    }

    public void setKnownIngressType(Class knownIngressType) {
        this.knownIngressType = knownIngressType;
    }


    public boolean isConvertableIngressEgress() {
        return convertableIngressEgress;
    }

    public void setConvertableIngressEgress(boolean convertableIngressEgress) {
        this.convertableIngressEgress = convertableIngressEgress;
    }

    public void computeTypeConversionRule() {
        if (knownIngressType != null && knownEgressType != null) {
             convertableIngressEgress = knownIngressType.isAssignableFrom(knownEgressType);
        }
    }

    public Object getValue(Object staticContext, VariableResolverFactory factory) {
      //  if (expressionParser == null) {
            return new ExpressionParser(factory, staticContext, tokenMap).parse();
      //  }
        
      //  return expressionParser.resetParser().parse();
    }
}

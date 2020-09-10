/*-
 * #%L
 * Coffee
 * %%
 * Copyright (C) 2020 i-Cell Mobilsoft Zrt.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package hu.icellmobilsoft.roaster.restassured;

import javax.enterprise.context.RequestScoped;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;

import hu.icellmobilsoft.coffee.cdi.logger.AppLoggerImpl;
import hu.icellmobilsoft.coffee.cdi.logger.LogContainer;
import hu.icellmobilsoft.coffee.cdi.logger.LogProducer;
import hu.icellmobilsoft.roaster.restassured.producer.RequestSpecificationProducer;
import hu.icellmobilsoft.roaster.restassured.producer.ResponseSpecificationProducer;
import hu.icellmobilsoft.roaster.restassured.producer.RestAssuredConfigProducer;
import io.smallrye.config.inject.ConfigExtension;

/**
 * Abstract base class for run IT tests with weld configuration
 *
 * @author mark.petrenyi
 * @author imre.scheffer
 */
@EnableWeld
public abstract class BaseConfigurableWeldIT {

    @WeldSetup
    private WeldInitiator weldInitiator = initWeld();

    private WeldInitiator initWeld() {
        @SuppressWarnings("unchecked")
        Weld weld = WeldInitiator.createWeld()//
                .addExtensions(ConfigExtension.class)//
                .addBeanClasses(RestAssuredConfigProducer.class, RequestSpecificationProducer.class, ResponseSpecificationProducer.class,
                        LogContainer.class, AppLoggerImpl.class, LogProducer.class);
        configureWeld(weld);

        WeldInitiator.Builder weldInitiatorBuilder = WeldInitiator.from(weld);
        weldInitiatorBuilder.activate(RequestScoped.class);
        configureWeldInitiatorBuilder(weldInitiatorBuilder);
        return weldInitiatorBuilder.build();
    }

    /**
     * Overridable method for configuring weld
     *
     * @param weld input weld instance for additional settings
     */
    protected void configureWeld(Weld weld) {

    }

    /**
     * Overridable method extending weld through builder
     *
     * @param weldInitiatorBuilder input WeldInitiator.Builder instance for
     *                             additional settings
     */
    protected void configureWeldInitiatorBuilder(WeldInitiator.Builder weldInitiatorBuilder) {

    }

}

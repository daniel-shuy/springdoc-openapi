/*
 *
 *  *
 *  *  *
 *  *  *  * Copyright 2019-2020 the original author or authors.
 *  *  *  *
 *  *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  *  * you may not use this file except in compliance with the License.
 *  *  *  * You may obtain a copy of the License at
 *  *  *  *
 *  *  *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *  *  *
 *  *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  *  * See the License for the specific language governing permissions and
 *  *  *  * limitations under the License.
 *  *  *
 *  *
 *
 *
 */

package test.org.springdoc.api.app32;

import org.springdoc.core.customizers.DataRestDelegatingMethodParameterCustomizer;
import org.springdoc.core.customizers.DelegatingMethodParameterCustomizer;
import org.springdoc.core.providers.RepositoryRestConfigurationProvider;
import org.springdoc.core.providers.SpringDataWebPropertiesProvider;
import org.springdoc.data.rest.SpringDocDataRestConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.TestPropertySource;
import test.org.springdoc.api.AbstractSpringDocTest;

import java.util.Optional;

@TestPropertySource(properties = "spring.data.web.sort.sort-parameter=sorts")
@EnableAutoConfiguration(exclude = {
		RepositoryRestMvcAutoConfiguration.class, SpringDocDataRestConfiguration.class
})
public class SpringDocApp32Test extends AbstractSpringDocTest {

	@SpringBootApplication
	static class SpringDocTestApp {
		// We only need to test spring-web with Sort, without the use of spring-data-rest-starter
		@Bean
		@ConditionalOnMissingBean
		@Lazy(false)
		DelegatingMethodParameterCustomizer delegatingMethodParameterCustomizer(Optional<SpringDataWebPropertiesProvider> optionalSpringDataWebPropertiesProvider, Optional<RepositoryRestConfigurationProvider> optionalRepositoryRestConfiguration) {
			return new DataRestDelegatingMethodParameterCustomizer(optionalSpringDataWebPropertiesProvider, optionalRepositoryRestConfiguration);
		}
	}

}

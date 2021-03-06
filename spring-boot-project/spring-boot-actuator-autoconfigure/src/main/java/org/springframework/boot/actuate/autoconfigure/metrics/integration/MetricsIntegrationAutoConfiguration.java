/*
 * Copyright 2012-2018 the original author or authors.
 *
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
 */

package org.springframework.boot.actuate.autoconfigure.metrics.integration;

import io.micrometer.core.instrument.MeterRegistry;

import org.springframework.boot.actuate.autoconfigure.metrics.export.simple.SimpleMetricsExportAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.support.management.micrometer.MicrometerMetricsFactory;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for Spring Integration Micrometer
 * support.
 *
 * @author Phillip Webb
 * @author Gary Russell
 * @since 2.0.0
 */
@Configuration
@ConditionalOnClass({ EnableIntegration.class, MeterRegistry.class })
@AutoConfigureAfter({ IntegrationAutoConfiguration.class,
		SimpleMetricsExportAutoConfiguration.class })
@ConditionalOnBean(MeterRegistry.class)
public class MetricsIntegrationAutoConfiguration {

	@Bean
	public MicrometerMetricsFactory integrationMicrometerMetricsFactory(
			MeterRegistry meterRegistry) {
		return new MicrometerMetricsFactory(meterRegistry);
	}

}

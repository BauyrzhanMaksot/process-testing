/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. Camunda licenses this file to you under the Apache License,
 * Version 2.0; you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.camundacon.tutorial.testing.delegate;


import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.camundacon.tutorial.testing.api.BookingService;

/**
 * This is an Ejb which is invoked from a Service task
 *
 */
@Named("checkSeatsDelegate")
@Stateless
public class CheckSeatsDelegate implements JavaDelegate {

  private final static Logger LOGGER = LoggerFactory.getLogger(CheckSeatsDelegate.class.getName());

  @Inject
  private BookingService bookingService;

  public void execute(DelegateExecution execution) throws Exception {
    LOGGER.info("\n\n\n This checks the seats are still available \n\n\n");
    execution.setVariable("available", bookingService.checkSeats((String) execution.getVariable("orderId")));
  }

}

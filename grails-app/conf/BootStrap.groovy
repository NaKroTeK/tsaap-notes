import grails.util.Environment
import groovy.sql.Sql
import org.tsaap.BootstrapService

import java.util.logging.Level

/*
* Copyright 2013 Tsaap Development Group
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

class BootStrap {

  BootstrapService bootstrapService

  def init = { servletContext ->
    bootstrapService.initializeRoles()
    Environment.executeForCurrentEnvironment {
      development {
        Sql.LOG.level = Level.FINE
        bootstrapService.inializeDevUsers()
        bootstrapService.initializeDevContext()
      }
      demo {
        bootstrapService.inializeDevUsers()
        bootstrapService.initializeDevContext()
      }
    }
  }

  def destroy = {}
}

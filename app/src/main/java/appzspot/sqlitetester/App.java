package appzspot.sqlitetester;

import android.app.Application;

import com.appzspot.easysql.config.Config;

/**
 * Created by Muhammad Faisal Nadeem on 10/25/2017.
 * Copyright © 2017 by Muhammad Faisal Nadeem
 * <p>
 * All information contained herein is, and remains
 * the property of Muhammad Faisal Nadeem. No part of this document
 * may be reproduced, distributed, or transmitted in any form or by any means
 * without the prior written permission of the publisher.
 * <p>
 * For permission request write to :-
 * <p>
 * Muhammad Faisal Nadeem.
 * mfaisalnadeem@hotmail.com
 */

public class App extends Application {

   @Override
   public void onCreate () {
      super.onCreate ();
      Config.init (  this , "testdb" , 1 , "appzspot.sqlitetester.model.dto" );
      Config.loggingEnabled = true;

   }
}

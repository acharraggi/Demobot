/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

// this method displays status and a counter to the ftc-dashboard, basic telemetry using "packet".
// I think there's an easier way.

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

/*
 * Demonstrates an empty iterative OpMode
 */
@Autonomous(name = "Test Dashboard", group = "Dashboard")

public class TestDashboard extends OpMode {

  private ElapsedTime runtime = new ElapsedTime();
  FtcDashboard dashboard = FtcDashboard.getInstance();
  TelemetryPacket packet = new TelemetryPacket();
  int x;

  /**
   * This method will be called once, when the INIT button is pressed.
   */
  @Override
  public void init() {

    telemetry.addData("Status", "Initialized");
    packet.put("status", "alive");
    dashboard.sendTelemetryPacket(packet);
    x = 0;
  }

  /**
   * This method will be called repeatedly during the period between when
   * the init button is pressed and when the play button is pressed (or the
   * OpMode is stopped).
   */
  @Override
  public void init_loop() {
  }

  /**
   * This method will be called once, when the play button is pressed.
   */
  @Override
  public void start() {
    runtime.reset();
  }

  /**
   * This method will be called repeatedly during the period between when
   * the play button is pressed and when the OpMode is stopped.
   */
  @Override
  public void loop() {
    telemetry.addData("StatusX", "Run Time: " + runtime.toString());

    x = x + 1;
    packet.put("status", "running");
    packet.put("x", x);
    dashboard.sendTelemetryPacket(packet);

/*    packet.fieldOverlay()
            .setStrokeWidth(1)
            .setStroke("red")
            .setFill("green")
            .setAlpha(1.0)
            .fillRect(20, 20, 40, 40);;*/

//    packet.fieldOverlay()
//            .setFill("blue")
//            .fillRect(-20, -20, 40, 40);

    packet.field();
    packet.fieldOverlay()
            // draw rectangle in the original field frame
            .fillRect(0, 0, 20, 20)
            // shift the field to be anchored in the center of the left side
            .setTranslation(0, 6 * 12)
            // draw rectangle in the new field frame
            .fillRect(0, 0, 20, 20);
    dashboard.sendTelemetryPacket(packet);
  }

  /**
   * This method will be called once, when this OpMode is stopped.
   * <p>
   * Your ability to control hardware from this method will be limited.
   */
  @Override
  public void stop() {

  }
}

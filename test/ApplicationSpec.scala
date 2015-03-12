package test

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

class ApplicationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication{
      route(FakeRequest(GET, "/boum")) must beNone
    }

    "render the index page" in new WithApplication{
      val home = route(FakeRequest(GET, "/")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("Your new application is ready.")
    }

    "render the soap page" in new WithApplication{
      //running(FakeApplication()) {

      val body = """<name>Sam</name>"""
      val request = FakeRequest(POST, "/soap").withTextBody(body).withHeaders(ACCEPT -> "text/xml")
      val soapResult = route(request).get


      status(soapResult) must equalTo(OK)

      println(contentType(soapResult))
      println(contentAsString(soapResult))

      contentType(soapResult) must beSome.which(_ == "text/xml")
      contentAsString(soapResult) must contain ("Hello")

      //}

    }
  }
}

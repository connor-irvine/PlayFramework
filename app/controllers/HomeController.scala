package controllers

import com.sun.xml.internal.bind.v2.TODO
import javax.inject._
import play.api.data
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import models.Task



/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */



class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport {
  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index = Action {
    Redirect(routes.HomeController.tasks)
  }

  def tasks = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index(Task.all(), taskForm))

  }

  def newTask = Action { implicit request =>
    taskForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(Task.all(), errors)),
      label => {
        Task.create(label)
        Redirect(routes.HomeController.tasks)
      }
    )
  }

  def deleteTask(id: Long) = TODO


  val taskForm = Form(
    "label" -> nonEmptyText
  )
}
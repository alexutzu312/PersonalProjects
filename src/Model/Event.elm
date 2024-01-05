module Model.Event exposing (..)

import Html exposing (..)
import Html.Attributes exposing (class, classList)
import Model.Event.Category exposing (EventCategory(..))
import Model.Interval as Interval exposing (Interval)
import Debug exposing (toString)
import Model.Date exposing (year)
import Model.Date exposing (month)
import Html.Attributes exposing (href)


type alias Event =
    { title : String
    , interval : Interval
    , description : Html Never
    , category : EventCategory
    , url : Maybe String
    , tags : List String
    , important : Bool
    }


categoryView : EventCategory -> Html Never
categoryView category =
    case category of
        Academic ->
            text "Academic"

        Work ->
            text "Work"

        Project ->
            text "Project"

        Award ->
            text "Award"


sortByInterval : List Event -> List Event
sortByInterval events =
    events |> List.sortWith (\event1 event2 -> Interval.compare event1.interval event2.interval)
    --Debug.todo "Implement Event.sortByInterval"


view : Event -> Html Never
view event =
    div [classList[("event", True), ("event-important", event.important)]] [
    h3 [class "event-title"] [text <| event.title]
    ,p [class "event-description"] [event.description]
    ,p [class "event-category"] [text <| (toString event.category)]
    ,p [class "event-url"] [a [ href (Maybe.withDefault event.title event.url)] [text <| "URL"]]
    ,p [class "event-interval"] [Interval.view event.interval]
    ]
    --Debug.todo "Implement the Model.Event.view function"

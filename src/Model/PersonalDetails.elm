module Model.PersonalDetails exposing (..)

import Html exposing (..)
import Html.Attributes exposing (class, classList, id)
import Html.Attributes exposing (style)
import Html.Attributes exposing (href)
import Debug exposing (toString) 


type alias DetailWithName =
    { name : String
    , detail : String
    }


type alias PersonalDetails =
    { name : String
    , contacts : List DetailWithName
    , intro : String
    , socials : List DetailWithName
    }


view : PersonalDetails -> Html msg
view details =
     div [] [
        h1 [id "name"] [text <| details.name]
        ,em [id "intro"] [text <| details.intro]
        ,p[](details.contacts |> List.map (\det -> div [class "contact-detail"] [text <| (det.name ++ " " ++ det.detail)]))
        ,p[](details.socials |> List.map (\det -> div [class "social-link"] [a [href det.detail] [text <| det.name]]))
     ]
    --Debug.todo "Implement the Model.PersonalDetails.view function"

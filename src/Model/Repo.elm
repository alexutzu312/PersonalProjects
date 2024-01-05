module Model.Repo exposing (..)

import Html exposing (..)
import Html.Attributes exposing (class, href)
import Json.Decode as De
import Model.Date exposing (Month(..))
import Debug exposing (toString)


type alias Repo =
    { name : String
    , description : Maybe String
    , url : String
    , pushedAt : String
    , stars : Int
    }


view : Repo -> Html msg
view repo =
    div [class "repo"] [ 
        h3[class "repo-name"][text <| repo.name] 
        ,p[class "repo-description"][text <| (Maybe.withDefault repo.name repo.description)] 
        ,p[class "repo-url"][a[href repo.url] [text <| repo.pushedAt]] 
        ,p[class "repo-stars"][text <| (toString(repo.stars))] ]
    --Debug.todo "Implement Model.Repo.view"


sortByStars : List Repo -> List Repo
sortByStars repos =
    repos |> List.sortWith (\repo1 repo2 -> Basics.compare (repo1.stars) (repo2.stars))
    --Debug.todo "Implement Model.Repo.sortByStars"


{-| Deserializes a JSON object to a `Repo`.
Field mapping (JSON -> Elm):

  - name -> name
  - description -> description
  - html\_url -> url
  - pushed\_at -> pushedAt
  - stargazers\_count -> stars

-}
decodeRepo : De.Decoder Repo
decodeRepo =
    De.map5 Repo 
    (De.field "name" De.string) 
    (De.maybe (De.field "description" De.string)) 
    (De.field "html_url" De.string) 
    (De.field "pushed_at" De.string) 
    (De.field "stargazers_count" De.int)
    --Debug.todo "Implement Model.Repo.decodeRepo"

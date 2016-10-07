(ns spellbook.state
  (:require [om.next :as om]
            [re-natal.support :as sup]))

(def app-state
  (atom {:app/msg   "Hello Clojure in Android!"
         :app/page  :list
         :app/prevrand []
         :app/new-moves []
         :app/days 0
         :app/moves [{:name           "Windmill"
                      :description    "Shoulder reel in front and behind"
                      :image          ""
                      :level          "3"
                      :category       "Reels"
                      :last-practiced 1474070310000}
                     {:name           "Basic Butterfly"
                      :description    "double dutch"
                      :image          ""
                      :level          "1"
                      :category       "Butterfly"
                      :last-practiced nil}
                     {:name           "Buzzsaw"
                      :description    "Wheel of death in front of you. Spinning 2 poi split time on the front, side plane"
                      :image          ""
                      :level          "2"
                      :category       "Reels"
                      :last-practiced nil}]}))

(defmulti read om/dispatch)

(defmethod read :default
  [{:keys [state]} k _]
  (let [st @state]
    (if-let [[_ v] (find st k)]
      {:value v}
      {:value :not-found})))

(defonce reconciler
         (om/reconciler
           {:state        app-state
            :parser       (om/parser {:read read})
            :root-render  sup/root-render
            :root-unmount sup/root-unmount}))
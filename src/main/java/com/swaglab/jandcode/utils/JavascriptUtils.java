package com.swaglab.jandcode.utils;

public class JavascriptUtils {
    /**
     * This method let open a new tab on browser on based of URL
     *
     * @param url this value indicate what is the new page web the system will load on new tab
     * @return Execute Javascript logic that open new tab.
     *
     */
    public static String openNewTab(String url){
        return String.format(
                "var url = `%s`;"+
                        "window.open(url);", url);
    }

    /**
     * This Method is a Javascript library that we need to apply if we use Gremlins.
     * Gremlins is a tools that we can simulate unexpected behaviors on web page with purpose is to
     * enhance robustness and quality of code.
     *
     * @return Load an anonymous function that execute Gremlins.js from unpkg.com repository
     */
    public static String libraryGremlins() {
        return String.format(
                "(function () {%n" +
                        " var xhr = new XMLHttpRequest();%n" +
                        " xhr.open(%n" +
                        " \"GET\",%n" +
                        " \"https://unpkg.com/gremlins.js@2.2.0/dist/gremlins.min.js\",%n" +
                        " true%n" +
                        " );%n" +
                        " xhr.onreadystatechange = function () {%n" +
                        " if (xhr.readyState === 4 && xhr.status === 200) {%n" +
                        " var gremlinsSourceCode = xhr.responseText;%n" +
                        " %n" +
                        " var script = document.createElement(\"script\");%n" +
                        " script.type = \"text/javascript\";%n" +
                        " script.text = gremlinsSourceCode;%n" +
                        " %n" +
                        " document.body.appendChild(script);%n" +
                        " }%n" +
                        " };%n" +
                        " xhr.send();%n" +
                        "})();"
        );
    }

    /**
     * This methods create group of gremlins and execute on actual page multiply random actions
     * for example: clicks, type, press buttons.
     * Gremlins.js have an anonymous function that contains a logic steps that gremlin will do
     *
     * @return an actions that gremlins will do on actual page browser
     */
    public static String executeGremlins() {
        return String.format(
                "gremlins\n" +
                        " .createHorde({\n" +
                        " species: [\n" +
                        " gremlins.species.clicker(),\n" +
                        " gremlins.species.toucher(),\n" +
                        " gremlins.species.formFiller(),\n" +
                        " gremlins.species.scroller(),\n" +
                        " gremlins.species.typer(),\n" +
                        " ],\n" +
                        " mogwais: [\n" +
                        " gremlins.mogwais.alert(),\n" +
                        " gremlins.mogwais.fps(),\n" +
                        " gremlins.mogwais.gizmo(),\n" +
                        " ],\n" +
                        " strategies: [gremlins.strategies.distribution()],\n" +
                        " })\n" +
                        " .unleash();"
        );
    }
}

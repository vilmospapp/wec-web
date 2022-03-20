<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="joy" uri="/jodd-joy" %>

<html>
  <head>
    <title><joy:text key="page-title-main" /></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes"/>

    <link href="css/main.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/style.css">

    <script src="js/main.js" type="text/javascript"></script>
    <script src="js/jquery-3.4.1.min.js"></script>
    <script>
      $(document).ready(function() {
        let loader = new lv();
        loader.initLoaderAll();
        loader.startObserving();
      });
    </script>
  </head>
  <body>
    <div class="styled-form">
      <form id="urlForm" action="/collector.collect" method="post">
        <div class="url-field">
          <label for="urlField"><joy:text key="url-field-label" /></label>
          <input type="text" name="urlField" id="urlField" placeholder="<joy:text key="url-field-placeholder" />" >
          <p class="error-msg" hidden="hidden"><joy:text key="invalid-url-format" /></p>
        </div>

        <div class="ssl">
          <label for="testSSL"><joy:text key="test-ssl" /></label>
          <input type="checkbox" id="testSSL" name="testSSL">
        </div>
        <div class="button-row">
          <input type="submit" id="verify" value="<joy:text key="submit" />" onclick="onSubmit()">
        </div>
      </form>

      <p class="description"><joy:text key="delivery-description"/></p>

    </div>
    <div class="full" hidden="hidden">
      <div id="dots" class="lv-dots lv-mid lg" data-label="<joy:text key="loading" />" hidden="hidden"></div>
    </div>
  <footer>
    <div class="footer">
      <a href="https://jodd.org"><img src="https://jodd.org/jodd-pp-128.png">&nbsp;Powered by Jodd</a>
    </div>

    <script>
      function onSubmit() {
        event.preventDefault();

        urlValue = $('#urlField').val();

        let url;

          try {
            url = new URL(urlValue);
          } catch (_) {
            $('#urlField').addClass('error');
            $('.error-msg').attr('hidden', false);
            return;
          }
          $('.styled-form').attr('hidden', true);
          $('.full').attr('hidden', false);
          $('#dots').attr('hidden', false);
          $('#urlForm').submit();
      }
    </script>
  </footer>
  </body>
</html>

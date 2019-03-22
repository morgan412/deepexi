<template>
	<div id="swagger-ui"></div>
</template>

<script>
  import SwaggerUI from 'swagger-ui'
  import 'swagger-ui/dist/swagger-ui.css'

  export default {
    mounted: function () {
        SwaggerUI({
          dom_id: '#swagger-ui',
          url: "http://localhost:8081/product/openapi/openapi.json",
          deepLinking: true,
        })
    },
  }
</script>

<style>
  html
  {
    box-sizing: border-box;
    overflow: -moz-scrollbars-vertical;
    overflow-y: scroll;
  }
    
  *,
  *:before,
  *:after
  {
    box-sizing: inherit;
  }
    
  body
  {
    margin:0;
    background: #fafafa;
  }
</style>

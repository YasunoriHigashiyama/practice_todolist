<template>
  <v-app>
    <v-main>
      <v-container fluid>
        <Alert />
      </v-container>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
import Alert from "./components/Alert.vue";
export default {
  components: { Alert },
  data: () => ({
    //
  }),
};
</script>

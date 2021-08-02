<template>
  <v-row>
    <CreateTaskDialog @addTask="addTask" />
    <v-spacer></v-spacer>
    {{ option }}
    <v-checkbox
      v-model="todo"
      class="ma-2"
      color="primary"
      hide-details
      label="TODO"
    ></v-checkbox>

    <v-checkbox
      v-model="done"
      class="ma-2 my-checkbox"
      color="primary"
      hide-details
      label="DONE"
    ></v-checkbox>

    <div class="text-center">
      <v-menu offset-y offset-x :close-on-content-click="false">
        <template v-slot:activator="{ on, attrs }">
          <v-chip
            class="ma-2"
            color="white"
            label
            text-color="gray"
            v-bind="attrs"
            v-on="on"
          >
            <v-icon left> mdi-sort </v-icon>
            Sort
          </v-chip>
        </template>

        <v-card class="mx-auto" min-height="400" width="344">
          <v-row align="center" justify="center">
            <v-col cols="12">
              <p class="text-center">Sort</p>
            </v-col>
            <v-btn-toggle
              tile
              v-model="sort"
              mandatory
              color="deep-purple accent-3"
            >
              <v-btn> START </v-btn>
              <v-btn> LIMIT </v-btn>
              <v-btn> CREATE </v-btn>
              <v-btn> TITLE </v-btn>
            </v-btn-toggle>
            <v-col cols="12">
              <p class="text-center">Order</p>
            </v-col>
            <v-btn-toggle
              tile
              v-model="orfer"
              mandatory
              color="deep-purple accent-3"
            >
              <v-btn> AS </v-btn>
              <v-btn> DE </v-btn>
            </v-btn-toggle>
            <v-col cols="12">
              <p class="text-center">表示件数</p>
            </v-col>
            <v-col>
              <v-slider
                v-model="number"
                thumb-color="deep-purple accent-3"
                thumb-label="always"
                max="100"
                min="10"
              ></v-slider>
            </v-col>
          </v-row>
        </v-card>
      </v-menu>
    </div>
    <v-chip class="ma-2" color="white" label text-color="gray">
      <v-icon left> mdi-magnify </v-icon>
      Search
    </v-chip>
  </v-row>
</template>
<script>
import CreateTaskDialog from "./CreateTaskDialog.vue";
export default {
  components: { CreateTaskDialog },
  data() {
    return { todo: true, done: true, sort: "", number: 20 };
  },
  computed: {
    option() {
      //As 昇順 De 降順

      let option = new Object();
      option["todo"] = this.todo;
      option["done"] = this.done;

      return option;
    },
  },
  methods: {
    addTask(task) {
      this.$emit("addTask", task);
    },
  },
};
</script>





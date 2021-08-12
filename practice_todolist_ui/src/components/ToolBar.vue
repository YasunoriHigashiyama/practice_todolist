<template>
  <v-row>
    <v-chip
      class="ma-2 mt-5"
      color="white"
      label
      text-color="gray"
      @click="dialogOpen"
    >
      <v-icon left> mdi-plus </v-icon>
      Add Task
    </v-chip>
    <CreateTaskDialog ref="createTaskDialog" @addTask="addTask" />
    <v-spacer></v-spacer>

    <v-checkbox
      v-model="defaultOption.todo"
      @change="onChange($event)"
      class="ma-2"
      color="primary"
      hide-details
      label="TODO"
    ></v-checkbox>

    <v-checkbox
      @change="onChange($event)"
      v-model="defaultOption.done"
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
              @change="onChange($event)"
              tile
              v-model="defaultOption.sort"
              mandatory
              color="deep-purple accent-3"
            >
              <v-btn
                v-for="list in sortList"
                :key="list.value"
                :value="list.sort"
                style="white-space: pre-wrap; word-wrap: break-word"
                >{{ list.value }}
              </v-btn>
            </v-btn-toggle>
            <v-col cols="12">
              <p class="text-center">Order</p>
            </v-col>
            <v-btn-toggle
              tile
              @change="onChange($event)"
              v-model="defaultOption.order"
              mandatory
              color="deep-purple accent-3"
            >
              <v-btn
                v-for="list in orderList"
                :key="list.icon"
                :value="list.order"
              >
                <v-icon>{{ list.icon }}</v-icon>
              </v-btn>
            </v-btn-toggle>

            <v-col cols="12">
              <p class="text-center mb-0 mt-4">表示件数</p>
            </v-col>

            <v-col cols="6">
              <v-select
                v-model="defaultOption.number"
                :items="numberList"
                item-text="number"
                single-line
                @change="onChange($event)"
                dense
              ></v-select>
            </v-col>
          </v-row>
        </v-card>
      </v-menu>
    </div>
    <v-text-field
      v-model="defaultOption.search"
      class="mb-2"
      prepend-inner-icon=" mdi-magnify"
      label="Search"
      outlined
      hide-details
      dense
      @input="input($event)"
    ></v-text-field>
  </v-row>
</template>

<script>
import CreateTaskDialog from "./CreateTaskDialog.vue";
export default {
  components: { CreateTaskDialog },

  data() {
    return {
      select: { number: 20 },
      numberList: [
        { number: 10 },
        { number: 20 },
        { number: 30 },
        { number: 40 },
        { number: 50 },
      ],
      sortList: [
        { value: "START\nDATE", sort: "start" },
        { value: "LIMIT\nDATE", sort: "limit" },
        { value: "CREATE\nDATE", sort: "create" },
        { value: "TITLE", sort: "title" },
      ],
      orderList: [
        { icon: "mdi-menu-up", order: "ac" },
        { icon: "mdi-menu-down", order: "de" },
      ],

      defaultOption: {
        todo: true,
        done: true,
        sort: "limit",
        order: "de",
        number: 20,
        search: "",
      },
    };
  },
  computed: {},

  mounted() {},

  methods: {
    addTask(task) {
      this.dialog = false;
      this.$emit("addTask", task);
    },
    updateOption() {
      this.$emit("option", this.defaultOption);
    },
    onChange() {
      this.updateOption();
    },
    input() {
      this.updateOption();
    },
    dialogOpen() {
      this.$refs.createTaskDialog.open();
    },
    getOption() {
      return this.defaultOption;
    },
  },
};
</script>





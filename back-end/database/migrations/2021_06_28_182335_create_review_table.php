<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateReviewTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('review', function (Blueprint $table) {
            $table->unsignedBigInteger('user_id')->references('id')->on('users');
            $table->string('student_name')->references('name')->on('users');
            $table->string('class_name');
            $table->string('class_code');
            $table->string('specialization');
            $table->boolean('decision')->nullable(); //toegelaten = true, afgewezen = false
            $table->text('description');

            // $table->unsignedBigInteger('class_id');
            // $table->string('period');
            // $table->string('preference'); //eerste of tweede keuze
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('review');
    }
}
